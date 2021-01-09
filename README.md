Problem statement:

Many companies like to list their phone numbers using the letters printed on most
telephones. This makes the number easier to remember for customers. An example
may be 1-800-FLOWERS

This coding challenge is to write a program that will show a user possible matches for a
list of provided phone numbers.

Your program should be a command line application that reads from files specified as
command-line arguments or STDIN when no files are given. Each line of these files will
contain a single phone number. Alternatively, you can develop a web UI to accept the
files as upload or an API that accepts the files as input.

For each phone number read, your program should output all possible word
replacements from a dictionary. Your program should try to replace every digit of the
provided phone number with a letter from a dictionary word; however, if no match can
be made, a single digit can be left as is at that point. No two consecutive digits can
remain unchanged and the program should skip over a number (producing no output) if
a match cannot be made.

Your program should allow the user to set a dictionary with the -d command-line option,
but it's fine to use a reasonable default for your system. The dictionary is expected to
have one word per line.

All punctuation and whitespace should be ignored in both phone numbers and the
dictionary file. The program should not be case sensitive, letting "a" == "A". Output
should be capital letters and digits separated at word boundaries with a single dash (-),
one possible word encoding per line.


Run Instructions:

1. Run the class named App.java (com.phone.number.app) with following 3 usecases.
 -  If argument given with -d add <word1> <word2>	- it adds above words to the dictionary
 - filepath - runs the program for numbers specified in the file and prints the possible matches
 - <No command line arguments> - It asks for phonenumber on command line input and prints output
		

The dictionary words are stored in data/word-dictionary.txt
The phone directory words are configured in data/phone-dict.txt 

Handled Use cases for attached dictionaries:

1. If input is 225563, 
	it would print BALL-ME, CALL-ME
2. For input 2225563,
	2-BALL-ME, 2-CALL-ME
3. For input 2255263
	BALL-2-ME, CALL-2-ME
4. For input 22225563
	No matches found
	
Scope of improvement:
1. Pick words intelligently based on parts of speech and ordering
# 1800-CODING-CHALLENGE
