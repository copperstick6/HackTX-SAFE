import keys
import urllib.request
import json
import pprint
import sys


def getWebUrl(address1, address2):
    s = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&"
    for i in str(address1):
        if(i = " "):
            tempS+="+"
        else:
            tempS+=
    s+= tempS + "&destinations="
    for i in str(address2):
        if(i = " "):
            tempS2+="+"
        else:
            tempS2+=
    s+=tempS + "&key=" + key.GoogleMapsKey
    return s

def getDistance(address1, address2):
    webUrl = urllib2.urlrequest.urlopen(getWebUrl(address1, address2))
    if (webUrl.getcode() == 200):
        data = webUrl.read()
        theJSON = json.loads(data)
        s =theJSON["rows"]["elements"]["distance"]
        s = s[:-3]
        return s
