import keys
import urllib.request
import json
import pprint
import sys
import codecs

def getWebUrl(address1, address2):
    s = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
    tempS = ""
    tempS2 = ""
    for i in str(address1):
        if(i == " "):
            tempS+="+"
        else:
            tempS+= i
    s+= tempS + "&destinations="
    for i in str(address2):
        if(i == " "):
            tempS2+="+"
        else:
            tempS2+= i
    tempKey = keys.GoogleMapsKey()
    s+=tempS2 + "&key=" + tempKey
    return s

def getDistance(address1, address2):
    webUrl1 = urllib.request.urlopen(getWebUrl(address1, address2)).read().decode('utf8')
    webUrl = urllib.request.urlopen(getWebUrl(address1, address2))
    if (webUrl.getcode() == 200):
        str_response = webUrl.read().decode('utf-8')
        obj = json.loads(str_response)
        s =obj["rows"][0]["elements"][0]["distance"]["value"]
        return s

#distance in meters
print(getDistance("6813 Beverly Glen Drive", "201 East 21st Street"))
