class Etablissement:
    def __init__(self):
        self.spatialReference: SpatialReference = SpatialReference()
        self.candidates = []


class SpatialReference:
    def __init__(self):
        self.wkid: int = 0
        self.latestWkid: int = 0


class Candidates:
    def __init__(self):
        self.address: str = ''
        self.location: Location = Location()
        self.scores: str = ''
        self.attributes: Attributes = Attributes()


class Location:
    def __init__(self):
        self.x: float = 0.0
        self.y: float = 0.0


class Attributes:
    def __init__(self):
        self.something = ""
        pass
