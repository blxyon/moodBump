#!/usr/bin/env python
# coding: utf-8

import random
import spotipy
from spotipy.oauth2 import SpotifyClientCredentials

cid = '677b8b7d14114a95991c84bf3f32da5b'
secret = 'a03555397e28462da65cf42fbc5e9803'

client_credentials_manager = SpotifyClientCredentials(client_id=cid, client_secret=secret)
sp = spotipy.Spotify(client_credentials_manager=client_credentials_manager)

# Playlist IDs - maybe put in dictionary? 
def getPlaylists():
    # Happy 
    good_energy = '37i9dQZF1DWURugcFfOfEH'
    happy_hits = '37i9dQZF1DXdPec7aLTmlC'
    feelin_good = '37i9dQZF1DX9XIFQuFvzM4'
    serotonin = '37i9dQZF1DWYMroOc5KTTh'
    happy_jazz = '37i9dQZF1DX5YTAi6JhwZm'
    happy = [good_energy, happy_hits, feelin_good, serotonin, happy_jazz]


    # Indifferent
    mood_booster = '37i9dQZF1DX3rxVfibe1L0'
    summer_in_the_garden = '37i9dQZF1DWSewYkkEomBp'
    duvet_day = '37i9dQZF1DXdNR7UbdVQiC'
    wholesome = '37i9dQZF1DXaiLfJ2acJnZ'
    dazed = '37i9dQZF1DX2qBJch9g3qw'
    indifferent = [mood_booster, summer_in_the_garden, duvet_day, wholesome, dazed]

    # Sad 
    idk = '37i9dQZF1DX59NCqCqJtoH'
    sad_songs = '37i9dQZF1DX7qK8ma5wgG1'
    life_sucks = '37i9dQZF1DX3YSRoSdA634'
    all_the_feels = '37i9dQZF1DX7gIoKXt0gmx'
    melantronic = '37i9dQZF1DX39ATYW02fre'
    sad = [idk, sad_songs, life_sucks, all_the_feels, melantronic]
    
    return happy, indifferent, sad 

# Reccomends a playlist based on mood
def reccomend(mood="positive"):
    happy, indifferent, sad = getPlaylists()

    n = random.randint(0,4)
    
    if mood=="extremely positive" or mood=="very positive" or mood=="positive":
        pl = sp.playlist(happy[n])
    elif mood=="indifferent":
        pl = sp.playlist(indifferent[n])
    elif mood=="negative" or mood=="very negative" or mood=="extremely negative":
        pl = sp.playlist(sad[n])
    
    return pl