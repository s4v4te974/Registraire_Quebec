import uuid

import pandas as pd
import numpy as np
import requests as requests
from requests import Session, Response
from requests.adapters import HTTPAdapter
from urllib3 import Retry

import utils.const as ct

from models.Coordinate import EtabsGeo, AddressLocalite

url: str = (
    'https://servicescarto.mern.gouv.qc.ca/pes/rest/services/Territoire/AdressesQuebec_Geocodage/GeocodeServer'
    '/findAddressCandidates?f=json&outSR=4326&singleLine=')


def filter_entreprise_file():
    filtered_chunks: list = []
    chunk_size: int = 100_000

    df = pd.read_csv(ct.ENTREPRISE_BASE, chunksize=chunk_size, iterator=True)

    for chunk in df:
        chunk['DAT_DEPO_DECLR'] = pd.to_datetime(chunk['DAT_DEPO_DECLR'])
        filtered_chunk = chunk.loc[
            ((chunk['IND_FAIL'] == ct.INDICE_FAILLITE) | chunk['IND_FAIL'].isnull()) &
            ((chunk['DAT_CESS_PREVU'] == ct.EMPTY) | (chunk['DAT_CESS_PREVU'].isnull())) &
            (chunk['COD_STAT_IMMAT'] == ct.STATUS_IMMAT) &
            (chunk['COD_FORME_JURI'] != ct.CODE_FORME_JURI) &
            (chunk['DAT_DEPO_DECLR'].dt.year >= ct.LAST_UPDATE)
            ]
        filtered_chunks.append(filtered_chunk)
    df = pd.concat(filtered_chunks)
    df = df.drop_duplicates(subset=['NEQ'])
    df['COD_ACT_ECON_CAE'] = pd.to_numeric(df['COD_ACT_ECON_CAE'], errors='coerce')
    df['NEQ'] = pd.to_numeric(df['NEQ'], errors='coerce')

    return df


def filter_other_file_by_neq(path: str, df_entreprise):
    df = pd.read_csv(path)
    # convert column to numeric
    df['NEQ'] = pd.to_numeric(df['NEQ'], errors='coerce')

    index_comparison = df['NEQ'].isin(df_entreprise['NEQ'])
    return df[index_comparison]


def filter_other_files_by_code(path: str, df_entreprise):
    df = pd.read_csv(path)
    # get only the one with the value that interest us
    df = df.loc[(df['TYP_DOM_VAL'] == 'ACT_ECON')]
    # convert column to numeric
    df['COD_DOM_VAL'] = pd.to_numeric(df['COD_DOM_VAL'], errors='coerce')
    # filter by entreprise
    index_comparison = df['COD_DOM_VAL'].isin(df_entreprise['COD_ACT_ECON_CAE'])
    df_domaine_filtered = df[index_comparison]
    # return only 2 column
    return df_domaine_filtered.drop('TYP_DOM_VAL', axis=1)


def rename_dfs(list_df):
    for df in list_df:
        df.rename(columns={col: col.lower() for col in df.columns}, inplace=True)


def generate_uuid(list_df):
    for df in list_df:
        df.insert(0, 'ID', [uuid.uuid4() for _ in range(len(df))])


def retrieve_data(address: str, localite: str):
    print(address)
    url_to_call: str = url + address.replace(' ', '+')

    retry_strategy: Retry = Retry(
        total=3,
    )
    session: Session = requests.session()
    session.mount("https://", HTTPAdapter(max_retries=retry_strategy))
    response: Response = session.get(url_to_call)

    if response.status_code == 200:
        return map_response(response, localite, address)
    else:
        print("unable to call ")


def map_response(response, df_localite: str, address: str):
    data = response.json()
    etablissements: list[EtabsGeo] = []
    localite: str = df_localite[:3] + ' ' + df_localite[-3:]

    for candidate_data in data['candidates']:
        etablissement = EtabsGeo()
        etablissement.address = candidate_data['address']
        etablissement.x = candidate_data['location']['x']
        etablissement.y = candidate_data['location']['y']
        etablissements.append(etablissement)

    etablissements = [obj for obj in etablissements if obj.address.endswith(localite)]

    if etablissements:
        etablissements[0].address = address + ';' + df_localite
        return etablissements[0]


def populate_coordonates(df):
    df['x_coordinate'] = np.nan
    df['y_coordinate'] = np.nan
    etablissements = []

    print(df.shape[0])

    # for test

    df = df.head(500)

    df_filtered_unique = df.drop_duplicates(subset=['lign1_adr', 'lign4_adr'])
    df_filtered_no_valid_address = df_filtered_unique.dropna(subset=['lign1_adr', 'lign4_adr'])
    addresses: list[AddressLocalite] = (df_filtered_no_valid_address[['lign1_adr', 'lign4_adr']]
                                        .apply(lambda row: AddressLocalite(row['lign1_adr'], row['lign4_adr']),
                                               axis=1).tolist())

    print(len(addresses))
    print('calling WS')
    for address in addresses:
        etab_geo: EtabsGeo = retrieve_data(address.address, address.localite)
        if etab_geo:
            etablissements.append(etab_geo)

    print('map df attributes')
    for etab in etablissements:
        address = etab.address[:etab.address.index(';')].strip()
        localite = etab.address[etab.address.index(';')+1:].strip()

        condition = ((df['lign1_adr'] == address) & (df['lign4_adr'] == localite))

        print(f"Updating {sum(condition)} rows with x={etab.x} and y={etab.y}")

        df.loc[condition, 'x_coordinate'] = etab.x
        df.loc[condition, 'y_coordinate'] = etab.y

    return df
