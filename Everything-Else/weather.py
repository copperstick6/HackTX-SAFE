import keys
import urllib.request
import json
import codecs


#format of the returned list:
#dayPartName (nights and days), grassPollenIndex, grassPollenCategory, treePollenIndex, treePollenCategory,
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
        i = 0
        while i < (len(obj["pollenForecast12hour"]["daypartName"])):
            list2.append(obj["pollenForecast12hour"]["daypartName"][i])
            list2.append(obj["pollenForecast12hour"]["grassPollenIndex"][i])
            list2.append(obj["pollenForecast12hour"]["grassPollenCategory"][i])
            list2.append(obj["pollenForecast12hour"]["treePollenIndex"][i])
            list2.append(obj["pollenForecast12hour"]["treePollenCategory"][i])
            list2.append(obj["pollenForecast12hour"]["ragweedPollenIndex"][i])
            list2.append(obj["pollenForecast12hour"]["ragweedPollenCategory"][i])
            list1.append(list2)
            list2 = []
            i+=1
    return list1
