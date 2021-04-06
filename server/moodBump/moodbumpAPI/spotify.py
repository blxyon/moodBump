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
    
    # Gym 
    running_uk = '37i9dQZF1DWZ2xRu8ajLOe'
    level_up = '37i9dQZF1DX7g6vTI60Om0'
    gym_uk = '37i9dQZF1DWSWA2pLcO5dt'
    sweat_it_out = '37i9dQZF1DWVFO9vNZ6ym4'
    rap_workout = '37i9dQZF1DX76t638V6CA8'
    gym = [running_uk, level_up, gym_uk, sweat_it_out, rap_workout]
    
    # Sleep
    sleepy = '37i9dQZF1DWZd79rJ6a7lp'
    night_rain = '37i9dQZF1DXbcPC6Vvqudd'
    nightstorms = '37i9dQZF1DX4aYNO8X5RpR'
    white_noise = '37i9dQZF1DWUZ5bk6qqDSy'
    daydreamer = '37i9dQZF1DXdbkmlag2h7b'
    sleep = [sleepy, night_rain, nightstorms, white_noise, daydreamer]
    
    # Party
    motive = '37i9dQZF1DWVt4VCv1XXbY'
    family_party = '37i9dQZF1DWVyasWqYs9Zi'
    girls_night = '37i9dQZF1DX0Uv9tZ47pWo'
    bollywood_dance_music = '37i9dQZF1DX8xfQRRX1PDm'
    disco_hilife = '37i9dQZF1DXbS8bPVXXR2B'
    party = [motive, family_party, girls_night, bollywood_dance_music, disco_hilife]
    
    # Love
    love_yourself = '37i9dQZF1DWVR1Ngoan99K'
    timeless_love_songs = '37i9dQZF1DX7rOY2tZUw1k'
    romantic_ballads = '37i9dQZF1DX3tuWZaHjp5y'
    ily_x = '37i9dQZF1DX3NTaVu69yI9'
    love_letter = '37i9dQZF1DX38lOuCWlLV1'
    love = [love_yourself, timeless_love_songs, romantic_ballads, ily_x, love_letter]
    
    # Work 
    revision_ballads = "37i9dQZF1DX7sjxEGUAAaW"
    nature_sounds = '37i9dQZF1DX4PP3DA4J0N8'
    deep_focus = '37i9dQZF1DWZeKCadgRdKQ'
    brain_food = '37i9dQZF1DWXLeA8Omikj7'
    coding_mode = '37i9dQZF1DX5trt9i14X7j'
    work = [revision_ballads, nature_sounds, deep_focus, brain_food, coding_mode]
    
    return happy, indifferent, sad, gym, sleep, party, love, work 

# Reccomends a playlist based on mood
def reccomend(mood, k):
    happy, indifferent, sad, gym, sleep, party, love, work = getPlaylists()
    playlists = []

    # 1. Mood reccommendation 
    n = random.randint(0,4)
    if mood=="extremely positive" or mood=="very positive" or mood=="positive":
        pl = sp.playlist(happy[n])
    elif mood=="indifferent":
        pl = sp.playlist(indifferent[n])
    elif mood=="negative" or mood=="very negative" or mood=="extremely negative":
        pl = sp.playlist(sad[n])    
    playlists.append(pl)
    
    # 2. Keyword reccomentation
    n = random.randint(0,4)
    if k=="gym":
        pl = sp.playlist(gym[n])
        playlists.append(pl)
    elif k=="sleep":
        pl = sp.playlist(sleep[n])
        playlists.append(pl)
    elif k=="party":
        pl = sp.playlist(party[n])
        playlists.append(pl)
    elif k=="love":
        pl = sp.playlist(party[n])
        playlists.append(pl)
    elif k=="work":
        pl = sp.playlist(party[n])
        playlists.append(pl)
    else:
        playlists.append(happy[n])
    
    return playlists