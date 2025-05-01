# ChadMC

## About

Adds two commands to interact with a flask web server connected to the Google Gemini API.
Instancing is based on player names so every player gets their own personal Chad. In the future I plan on updating this to use the world for instancing so players can share a Chad on their server.

### Commands

/tellchad \<message>. Sends message to Chad and prints response to the chat\
/resetchad. Resets the chatbot effectively clearing its history

### Host your own Chad
https://github.com/Icyfiremario/gemini-mc-server

### TODO
- [x] Send full json data to server for user and world differentiation
- [ ] Proper error handling
- [x] Async HTTP requests to prevent minecraft from lagging out when you try to talk to Chad
