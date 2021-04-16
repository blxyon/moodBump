#!/usr/bin/env python
# coding: utf-8

from .sentiment import analyse
from rest_framework.decorators import api_view

@api_view(['POST'])
def analyse_sentiment(request):
    output = analyse(str(request.body))
    return Response(output)