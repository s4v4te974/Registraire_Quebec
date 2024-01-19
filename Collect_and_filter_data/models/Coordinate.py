class EtabsGeo:

    def __init__(self, address: str = '', x: float = 0, y: float = 0):
        self.address: str = address
        self.x: float = x
        self.y: float = y


class AddressLocalite:
    def __init__(self, address: str = '', localite: str = ''):
        self.address: str = address
        self.localite: str = localite
