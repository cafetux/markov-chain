package org.marpunk.infra.file;

import org.junit.Test;
import org.marpunk.core.Sentence;
import org.marpunk.core.word.SimpleWord;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class SentenceShouldBeSerializable {


    private static final String SAVE_FILENAME = "test.serial";

    @Test
    public void should_be_ponctuation_proof() throws Exception {
        SentenceFilesLoader loader = new SentenceFilesLoader(path("text/ponct"));
        List<Sentence> sentences = loader.load();

        save(sentences);
        sentences = load();

        assertThat(sentences.get(0)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word(";"), word("pénélope"), word("imbécile"), word(","))));
        assertThat(sentences.get(1)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word("!"), word("pénélope"), word("imbécile"), word(","))));
        assertThat(sentences.get(2)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word("?"), word("pénélope"), word("imbécile"), word(","))));
        assertThat(sentences.get(3)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word(":"), word("pénélope"), word("imbécile"), word(","))));
    }


    private SimpleWord word(String imbécile) {
        return SimpleWord.from(imbécile);
    }

    private Path path(String path) throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(path).toURI());
    }

    private static void save(List<Sentence> load) {
        try {

            FileOutputStream fos = new FileOutputStream(SAVE_FILENAME);

            // création d'un "flux objet" avec le flux fichier
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            try {
                // sérialisation : écriture de l'objet dans le flux de sortie
                oos.writeObject(load);
                // on vide le tampon
                oos.flush();
            } finally {
                //fermeture des flux
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    private static List<Sentence> load() {

        try {
            // ouverture d'un flux d'entrée depuis le fichier "personne.serial"
            FileInputStream fis = new FileInputStream(SAVE_FILENAME);
            // création d'un "flux objet" avec le flux fichier
            ObjectInputStream ois= new ObjectInputStream(fis);
            try {
                // désérialisation : lecture de l'objet depuis le flux d'entrée
                return (List<Sentence>) ois.readObject();
            } finally {
                // on ferme les flux
                try {
                    ois.close();
                } finally {
                    fis.close();
                }
            }
        } catch(IOException | ClassNotFoundException ioe) {
            throw new IllegalArgumentException(ioe);
        }
    }
}
