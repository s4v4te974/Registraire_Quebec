import psycopg2


def connect_to_database():
    try:
        conn = psycopg2.connect(
            dbname='postgres',
            user='postgres',
            password='postgres',
            host='localhost',
            port='5432'
        )

        return conn
    except psycopg2.Error as e:
        print("Error connecting to the database:")
        print(e)
