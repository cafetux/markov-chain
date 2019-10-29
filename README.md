[![Build Status](https://travis-ci.org/cafetux/markov-chain.svg?branch=master)](https://travis-ci.org/cafetux/markov-chain)

# Markov Chain

Ceci est une chaine de markov.

## How to

```
Path path ;//dossier où se trouvent les textes servant à alimenter la chaine

SentenceFilesLoader loader = new SentenceFilesLoader(path)

InMemoryWords words = new InMemoryWords();
loader.load().foreach(sentence -> words.save(sentence));

SentenceGenerator generator = new SentenceGenerator(words);
generator.generateSentence();

```

## Points d'entrée

La chaîne de markov manipule des Sentence, composées de Word.
Word est une interface exposant une clé et une valeur. Le SimpleWord founis prends un mot en paramètre, qui sera à la fois clé et valeur.

Il est aussi possible d'implémenter sa propre implémentation de word (prenant en compte des données grammaticales, ou phonétiques, ect).

Le découpage des phrases au format texte se fit dans le Splitter (autre interface, dont l'implémentation par défaut est le SimpleSplitter).

Pour utiliser un autre Splitter, qui découpe différement et fournit des implémentation de Word différente, initialisez votre générateur comme tel:

```

Path path ;//dossier où se trouvent les textes servant à alimenter la chaine

SentenceFilesLoader loader = new SentenceFilesLoader(path, new GrammarSplitter());

...
...


``` 


