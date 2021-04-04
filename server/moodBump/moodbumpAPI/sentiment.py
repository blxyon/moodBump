from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer

def analyse(data):
    text = data['text']
    print(text)
    analyzer = SentimentIntensityAnalyzer()
    vs = analyzer.polarity_scores(text)

    return {
        "sentiment": vs
    }
