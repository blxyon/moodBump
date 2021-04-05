#!/usr/bin/env python
# coding: utf-8

# In[45]:


import import_ipynb
from sentiment import analyse
from sentiment import getView
from nltk.tokenize import sent_tokenize


# In[56]:


# Split text into sentances 
text = "I feel like getting the mole on my back removed :("
sentances = sent_tokenize(text)
print(sentances)


# In[57]:


# For each sentance get the sentiment 
views = [] # [compound, view]
for i in range(len(sentances)):
    sentiment = analyse(sentances[i])
    compound, view = getView(sentiment)
    views.append([getView(sentiment)])
print(views)


# In[58]:


# Find mean of all sentances for overall mood 
getView(analyse(text))


# In[ ]:




