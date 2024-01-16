# AirportApp
Airport application that determines the shortest path to a destination through the use of various data structures and algorithims

In order for this project to function, two comma-separated values files must be included. These must be named airports.csv, and distances.csv.

The data in airports.csv includes airport codes (typically 3 letters), and their extended descriptions. This must be formatted as such:
LAX,Los Angeles (CA) - International
PHX,Pheonix (AZ) - Sky Harbor International

The data in distances.csv includes two unique airport codes, and the distance from the first to the second. It is important to note that this project functions through the use of a directed graph. Therefore, if back and forth travel is desired between two airports, make sure to include travel information from Airport 1 to Airport 2, and Airport 2 to Airport 1. This file must be formatted as such:
LAX,PHX,370
LAX,LAS,236
