import keys
import urllib.request
import json
import codecs

#list1 is a list of a list of strings, where each element in the original list is
#the list of elements
#each element's list's elements are as follows: (All elements in array are string)
#(title, date, coordinates, disaster type, description, affected countries)

def getEvents(list1):
    s = "https://api.sigimera.org/v1/crises?auth_token="
    s+= sigimeraAuth()
    webUrl  = urllib.request.urlopen(s)
    if(webUrl.getcode() == 200)
        counter = 0
        response = webUrl.read().decode('utf-8')
        theJSON = json.loads(response)
        for i in theJSON["_id"]
            try:
                list1.index(i["_id"])
            catch:
                counter++
            if(counter!=0):
                list2 = []
                list2.append(i["_id"]["dc_title"])
                list2.append(i["_id"]["dct_modified"])
                s = str(i["_id"]["foaf_based_near"][1]) + str(i["_id"]["foaf_based_near"][0])
                list2.append(s)
                list2.append(i["_id"]["dc_subject"][0])
                list2.append(["_id"]["dc_description"])
                if(i["_id"]["gn_parentCountry"][0]!=" "):
                    for j in i["_id"]["gn_parentCountry"]
                        k+=i["_id"]["gn_parentCountry"]
                    list2.append(k)
                else:
                    list2.append("N/A, no affected countries")
                list1.append(list2)
