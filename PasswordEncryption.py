#Encryption using PyCrypto

from Crypto.Cipher import AES
import base64
import os

#The key must be 16, 24 or 32 bytes long.

message = raw_input("Please enter a password, no longer than 32 characters.\n")

while len(message) > 32:
	message = raw_input("Please enter a password, no longer than 32 characters.\n")

message = message.rjust(32)
key = "HereWeGoBoisWoot"
#key = os.urandom(16)  

cipher = AES.new(key ,AES.MODE_ECB)
encrypted = base64.b64encode(cipher.encrypt(message))

print encrypted

decrypted = cipher.decrypt(base64.b64decode(encrypted))

print decrypted.strip()
