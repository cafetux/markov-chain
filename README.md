[![Build Status](https://travis-ci.org/cafetux/markov-chain.svg?branch=master)](https://travis-ci.org/cafetux/markov-chain)

# Markov Chain

Ceci est une chaine de markov.

# How to

```
Path path ;//dossier où se trouvent les textes servant à alimenter la chaine

SentenceFilesLoader loader = new SentenceFilesLoader(path)

InMemoryWords words = new InMemoryWords();
loader.load().foreach(sentence -> words.save(sentence));

SentenceGenerator generator = new SentenceGenerator(words);
generator.generateSentence();

```