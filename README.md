# EchoBot
VK API Bot, that quotes your messages

Steps to launch:
1. First of all, you should rename application_template.yml to application.yml
2. Select port in YML.
3. Register new VK community
4. Get following parameters from group's settings:
	-group-id - community id

  	-confirmation-token - token from Callback API settings to approve endpoint on VK's side

  	-access-token - Token to access VK API, register it in API settings.
5. Fill yml's properties
6. Install ngrok and launch it. Register tunnel to your port and copy it's URL.
7. Add copied URL in Callback API settings of the community. 
8. Launch App via EchobotApplication and validate URL.


If you need an easy demo, you can try this bot at https://vk.com/the_echo_bot
If the service is down, contact me on https://vk.com/id8136252

