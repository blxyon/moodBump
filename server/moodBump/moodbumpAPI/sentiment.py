#!/usr/bin/env python
# coding: utf-8

# In[65]:


# NLP library
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer


# In[66]:


def analyse(data):
    #text = data['text']
    #print(data)
    analyzer = SentimentIntensityAnalyzer()
    vs = analyzer.polarity_scores(data)
    return vs


# In[67]:


def getValues(sentiment):
    neg = sentiment['neg']
    pos = sentiment['pos']
    compound = sentiment['compound']
    return neg, pos, compound


# In[72]:


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


# In[73]:


# sentiment = analyse("I hate cats the most so much cats are vile")
# neg, pos, compound = getValues(sentiment)
# compound, view = getView(compound)
# print(view, compound)


# In[ ]:




