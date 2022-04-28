import requests

entries = []
for c_idx in range(ord("a"), ord("z") + 1):
	document = requests.get(f'https://absoluteshakespeare.com/glossary/{chr(c_idx)}.htm').content
	start_idx = document.find(b'<!-- #BeginEditable "content" -->') + 33
	if start_idx == ord("a"):
		start_idx = document[start_idx:].find(b"</b></p>\n                        <p>") + start_idx + 37
	end_idx = document[start_idx:].find(b'<!-- #EndEditable -->') + start_idx
	letter_list = document[start_idx:end_idx].split(b'<br>')
	for e in letter_list:
		entries.append(e)

with open("dictionary.txt", 'w') as file:
	for entry in entries:
		file.write(entry.decode('ISO-8859-1').strip() + '\n')
