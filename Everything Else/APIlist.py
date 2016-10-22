#Below is the list of all CONFIRMED APIs we are using.
#Twilio API: https://www.twilio.com/
#Google Maps API: https://developers.google.com/maps/documentation/ Python.
#Crises API: http://api.crisis.net/
#ReliefWeb API: http://www.programmableweb.com/api/reliefweb

#possible APIs:
#Amazon Alexa API
#IBM Watson???
#context.io for getting most valuable friends via email analysis
#indeed for job search after misplacemet by natural disasters
#Microsoft Azure for cloud computing/creating dev gateways/API management
#capital one for payment systems
#or chase, or both.

#Strategy for tomorrow:
#1. get base functionality done:
#Android guys get the app design down.
#backend devs focus on those 4 APIs, additional if necessary
#frontend figure out website
#2. testing and implementation.
#3. addition of extra functionalities
#4. roll out completed versions.


#we are going for the following:
#good neighbor award: $200 first place, 100 for second
#general placing, monitors, laptops, tablet, decending order
#national instruments rapid prototyping award
#mongoDB hack award: gift cards and trip


#flow chart of functionality

#New Users
#Step 1. Sign up, home location, phone number stored
            #-if signup exists, skip to Step 4.
#Step 2. Basic close contacts added by user choice
#Step 3. User acknowledges permissions
#Step 4: send current data to server, server checks safety
#           -If user is deemed safe, continue to home screen
#           -Otherwise, ask for safety of user
#               -If user says they are OK, continue to home screen
#               -Otherwise, give user choice to notify friends
                #   -friends notified
#Step 5: take user to home screen, monitor activities close to the user
#Step 6a: monitor activities of friends, safety of friends
#           -note that this functionality is only availiable if both Users
#           -designate each other as friends, so both users have the app
#           -invite contacts to the app via various platforms (facebook, SMS, email)
#Step 7: Rinse and repeat.
