# Encryption
  - Input files are typically .txt files
  - The input file can be run from the same directory as the class file.
  - If input file is not in the same directory, then use the full path name. Make sure to make all single backslashes into double backslashes.
  - Output file does need a file extention. None will be given.

###Caesar Cipher
This is a basic caesar cipher in which only letters are shifted and everything else stays the same.
  - The shift is the value by which each letter jumps forward for encryption and jumps back for decryption.
  - Characters such as spaces, parenthesis, and numbers are not changed.

###Simple Substitution Cipher
The is a simple substitution cipher in which all letters are matched to corrosponding letters.
  - The Substitution Key should be a list of 26 different letters seperated by commas. Don't have spaces! The first letter replaces the letter a, the seccond letter replaces b and so on and so forth.
  - Capital letters replace capital letters and lowercase letters replace lowercase letters.
  - Characters such as spaces, parenthesis, and numbers are not changed.

###Vigenère and Gronsfeld Cipher
This is a Vigenère and Gronsfeld cipher in which a word is used to determine shift size.
  - Shift key should be a single word.
  - If you decide to encrypt each line seperately, then the word starts from the begining at the beginning of each line.
