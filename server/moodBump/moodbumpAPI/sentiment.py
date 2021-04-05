#!/usr/bin/env python
# coding: utf-8



# NLP library
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
from nltk.tokenize import sent_tokenize



def analyse(data):
    text = data['text']
    analyzer = SentimentIntensityAnalyzer()
    vs = analyzer.polarity_scores(data)
    return {"mood" : vs} 




def getValues(sentiment):
    neg = sentiment['neg']
    pos = sentiment['pos']
    compound = sentiment['compound']
    return neg, pos, compound




def getView(sentiment):
    compound = sentiment['compound']
    
    if compound >= 0.8:
        view = "extremely positive"
    elif compound < 0.8 and compound >= 0.5:
        view = "very positive"
    elif compound < 0.5 and compound >= 0.2:
        view = "positive"
    elif compound < 0.2 and compound >= -0.2:
        view = "indifferent"
    elif compound < -0.2 and compound >= -0.5:
        view = "negative"
    elif compound < -0.5 and compound >= -0.8:
        view = "very negative"
    elif compound < -0.8:
        view = "extremely negative"
        
    return compound, view    



# Split text into sentences 
text = "I feel like getting the mole on my back removed :("
sentences = sent_tokenize(text)
print(sentences)



# For each sentence get the sentiment 
views = [] # [compound, view]
for i in range(len(sentences)):
    sentiment = analyse(sentences[i])
    compound, view = getView(sentiment)
    views.append([getView(sentiment)])
print(views)




# Find mean of all sentences for overall mood 
getView(analyse(text))

# sentiment = analyse("I hate cats the most so much cats are vile")
# neg, pos, compound = getValues(sentiment)
# compound, view = getView(compound)
# print(view, compound)






