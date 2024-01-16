import uuid

import pandas as pd
import utils.const as ct


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
