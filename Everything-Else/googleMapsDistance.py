import keys
import urllib.request
import json

def getWebUrl(address 1, address 2):

if (webUrl.getcode() == 200):


def getDistance(address1, address2):
    webUrl = urllib2.urlrequest.urlopen(getWebUrl(address1, address2))
    if (webUrl.getcode() == 200):
        data = webUrl.read()
        theJSON = json.loads(data)
        s =theJSON["rows"]["elements"]["distance"]
        s = s[:-3]
        return s
