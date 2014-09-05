import IPythonProcessor
import simplejson as json


class PythonProcessor(IPythonProcessor):
    def __init__(self):
        self.records_processed = 0

    def process(self, data):
        events = json.loads(data)

        if type(events) is not list:
            events = [events]

        for e in events:
            self.records_processed += 1
            print json.dumps(e)

