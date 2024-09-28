import os
from pyairtable import Api

class AirtableConnector:
    def __init__(self):
        self.api = Api(os.getenv("AIRTABLE_API_KEY"))

    def save_record(self):
        table = self.api.table('app6Nxzo495TjhD3n', 'tblELg5a4ZrkrFYMd')
        table.create({"Name": "Bob"})

