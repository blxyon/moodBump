#!/usr/bin/env python
# coding: utf-8

# NLP library
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer

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


# Main 
def analyse(data):
    text = data['text']
    
    sentiment = getSentiment(data)
    
    compound, mood = getMoodString(sentiment)
    
    # keywords = getKeywords(data)
    
    # playlist_recs = getPlaylists(mood)
    
    return {"mood" : mood} 
