import keys
import urllib.request
import json
import codecs


#format of the returned list:
#dayPartName (nights and days), grassPollenIndex, treePollenIndex, treePollenCategory,
#Ragweed PollenIndex, RagweedPollenCategory
def getPollen(days, lat, lon):
    if(((days == 3)or(days == 5)or(days == 7)or(days == 10)or(days == 15))==False):
        raise ValueError("Days must be 3,5,7,10,or 15")
    s = "https://api.weather.com/v2/indices/pollen/daypart/"
    s+= str(days) + "day?geocode="
    s+= str(lat) + "," + str(lon) + "&language=en-US&format=json&apiKey="
    s+= keys.blueChasmKey()
    webUrl = urllib.request.urlopen(s)
    list1 = []
    list2 = []
    if(webUrl.getcode() == 200):
        str_response = webUrl.read().decode('utf-8')
        obj = json.loads(str_response)
        for i in obj:
            list2.append(i["daypartName"][i])
            list2.append(i["grassPollenIndex"][i])
            list2.append(i[])
