import keys
import urllib.request
import json
import codecs

def getEvents(list1):
    s = "https://api.sigimera.org/v1/crises?auth_token="
    s+= keys.sigimeraAuth()
    webUrl  = urllib.request.urlopen(s)
    if(webUrl.getcode() == 200)
        response = webUrl.read().decode('utf-8')
        theJSON = json.loads(response)
