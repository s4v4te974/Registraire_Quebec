import os

# begin part download and unzip
# url to download file
URL_REGISTRE: str = ('https://www.registreentreprises.gouv.qc.ca/RQAnonymeGR/GR/GR03/GR03A2_22A_PIU_RecupDonnPub_PC'
                     '/FichierDonneesOuvertes.aspx')
DOWNLOAD_PATH: str = os.path.join(os.path.dirname(os.path.dirname(__file__)), r'download\\')
DOWNLOAD_FILE_PATH: str = os.path.join(DOWNLOAD_PATH, 'jeuxDonnees.zip')

# error message
FAILED_TO_DOWNLOAD: str = 'failed to download the file'
FAILED_TO_UNZIP: str = 'failed to unzip file'

# good message
DOWNLOAD_SUCCESS: str = 'Download files with success'
CONNEXION_SUCCESS: str = 'Connexion with server success : '
UNZIP_SUCCESS: str = 'Files unzip with success'

# Http utils
HEADERS: dict = {
    'Accept': '*/*',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 '
                  'Safari/537.3',
    'Connection': 'keep-alive',
}
# end part download and unzip

# start part filtering csv
ENTREPRISE_BASE: str = os.path.join(DOWNLOAD_PATH, 'Entreprise.csv')
TRANSFO_BASE: str = os.path.join(DOWNLOAD_PATH, 'ContinuationsTransformations.csv')
ETABLISSEMENT_BASE: str = os.path.join(DOWNLOAD_PATH, 'Etablissements.csv')
FUSCI_BASE: str = os.path.join(DOWNLOAD_PATH, 'FusionScissions.csv')
NOM_BASE: str = os.path.join(DOWNLOAD_PATH, 'Nom.csv')
DOMAINE_BASE: str = os.path.join(DOWNLOAD_PATH, 'DomaineValeur.csv')

# filter constant
STATUS_IMMAT: str = 'IM'
INDICE_FAILLITE: str = 'N'
CODE_FORME_JURI: str = 'IND'
LAST_UPDATE: int = 2014
EMPTY: str = ''
