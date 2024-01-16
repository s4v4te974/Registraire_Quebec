import cloudscraper
from utils import const as ct
import zipfile
import os.path


def download_and_unzip_file():
    download_files()
    unzip_files()


def download_files():
    scraper = cloudscraper.create_scraper()
    response = scraper.get(ct.URL_REGISTRE, headers=ct.HEADERS)
    if response.status_code == 200:
        print(ct.CONNEXION_SUCCESS, response.status_code)
        content = response.content
        with open(ct.DOWNLOAD_FILE_PATH, 'wb') as file:
            file.write(content)
    else:
        print(ct.FAILED_TO_DOWNLOAD, response.status_code)


def unzip_files():
    with zipfile.ZipFile(ct.DOWNLOAD_FILE_PATH, 'r') as jdzip:
        jdzip.extractall(ct.DOWNLOAD_PATH)
    os.remove(ct.DOWNLOAD_FILE_PATH)


def remove_file():
    files = os.listdir(ct.DOWNLOAD_PATH)
    for file_name in files:
        file_path = os.path.join(ct.DOWNLOAD_PATH, file_name)
        if os.path.isfile(file_path):
            os.remove(file_path)
