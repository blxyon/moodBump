#!/usr/bin/env python
# coding: utf-8

# NLP library
import statistics
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
from .spotify import reccomend

# Get sentiment dictionary 
def getSentiment(data):
    sia = SentimentIntensityAnalyzer()
    vs = sia.polarity_scores(data)
    return vs 

# Gets a descriptor of the mood 
def getMoodString(sentiment):
    compound = sentiment['compound']
    if compound >= 0.8:
        mood = "extremely positive"
    elif compound < 0.8 and compound >= 0.5:
        mood = "very positive"
    elif compound < 0.5 and compound >= 0.2:
        mood = "positive"
    elif compound < 0.2 and compound >= -0.2:
        mood = "indifferent"
    elif compound < -0.2 and compound >= -0.5:
        mood = "negative"
    elif compound < -0.5 and compound >= -0.8:
        mood = "very negative"
    elif compound < -0.8:
        mood = "extremely negative"    
    return compound, mood # returns the descriptor and the original value 

def getKeywords():
    # Instead of listing words do NLP to find similar? 
    gym_keywords = ["gym", "workout", "run", "sweat", "lifting", "weights", "exercise"]
    sleep_keywords = ["tired", "sleep", "bed", "alarm"]
    party_keywords = ["dance", "party", "club", "alcohol", "drinks", "friends", "gathering", "picnic"]
    love_keywords = ["relationship", "boyfriend", "partner", "wife", "husband", "date", "crush"]
    work_keywords = ["stress", "exam", "work", "revision", "homework", "procrastinate", "productive", "university", "school", "job"]
    # Could be a dict? 
    keywords = [["gym", gym_keywords], ["sleep", sleep_keywords], ["party", party_keywords], ["love", love_keywords], ["work", work_keywords]]
    return keywords

def findKeywords(data):
    keywords = getKeywords()
    kwords_in_text = []
    # Find keywords in text
    for i in range(len(keywords)):
        for j in range(len(keywords[i][1])):
            if keywords[i][1][j] in data:
                kwords_in_text.append(keywords[i][0])            
    k = statistics.mode(kwords_in_text)
    return k          
                
# Main 
def analyse(data):
#     text = data['text']

    sentiment = getSentiment(data)
    
    compound, mood = getMoodString(sentiment)
    
    keyword = findKeywords(data)
    
    playlists = reccomend(mood, keyword)
    playlists_refined = []
    for i in range(len(playlists)):
        playlist_dict = {'name': playlists[i]['name'], 'description': playlists[i]['description'], 'external_urls': playlists[i]['external_urls'], 'images': playlists[i]['images'], 'id': playlists[i]['id']}
        playlists_refined.append([playlist_dict])

        
    #print("You are", mood, "and you did", keyword, "so we reccomend playlists:", playlists[0]['name'], "and", playlists[1]['name'])
    return {"compound" : compund, "mood" : mood, "keyword" : keyword, "playlists" : playlists_refined} 




