package com.generation.tddquestionary;

import com.generation.tddquestionary.model.entities.Question;
import com.generation.tddquestionary.model.entities.Questionary;
import com.generation.tddquestionary.model.entities.Student;
import com.generation.tddquestionary.model.repositories.QuestionRepository;
import com.generation.tddquestionary.model.repositories.QuestionaryRepository;
import com.generation.tddquestionary.model.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class TddQuestionaryApplicationTests
{
	@Autowired
	QuestionaryRepository questionaryRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	StudentRepository studentRepository;

	@Test
	void primoTest()
	{
		//Question N - 1 Questionary N - 1 Student
		//creare database tddquestionary;

		Question question1 = new Question();
		question1.setText("Quanto fa 1+1");
		question1.setPoints(5);
		question1.setAnswer("fa 17");
		question1.setCorrect(false);


		Question question2 = new Question();
		question2.setText("Quanto fa 1+2");
		question2.setPoints(4);
		question2.setAnswer("fa 3");
		question2.setCorrect(true);

		Question question3 = new Question();
		question3.setText("Quanto fa 3+7");
		question3.setPoints(3);
		question3.setAnswer("fa 10");
		question3.setCorrect(true);

		Questionary questionary = new Questionary();
		questionary.setExamDate(LocalDate.now());
		questionary.setTopic("Addizioni");
		questionary.addQuestion(question1);
		questionary.addQuestion(question2);
		questionary.addQuestion(question3);

		//i punti totali possibili sono la somma dei punti di tutte le question
		assertEquals(12, questionary.getTotalPoints());

		//i punti fatti sono la somma dei punti delle domande corrette
		assertEquals(7, questionary.getCorrectPoints());

		//il voto in decimi è la traduzione del voto ottenuto in base 10
		//in questo caso sono 7 punti su 12    7/12 * 10 -> 5.83
		//voglio che sia arrotondato al mezzo punto più vicino (5.83 fa 6)
		//nel caso sia .25 o .75 esatto, siamo buoni, arrotondate per eccesso
		assertEquals(6.0, questionary.getVote());

		//ora cambio il punteggio della prima domanda
		question1.setPoints(6);
		//7/13 punti totali -> 5.38, deve fare 5.5
		assertEquals(5.5, questionary.getVote());

	}

	@Test
	void secondoTest()
	{
		Question question1 = new Question();
		question1.setText("Quanto fa 1+1");
		question1.setPoints(5);
		question1.setAnswer("fa 17");
		question1.setCorrect(false);


		Question question2 = new Question();
		question2.setText("Quanto fa 1+2");
		question2.setPoints(4);
		question2.setAnswer("fa 3");
		question2.setCorrect(true);

		Question question3 = new Question();
		question3.setText("Quanto fa 3+7");
		question3.setPoints(3);
		question3.setAnswer("fa 10");
		question3.setCorrect(true);

		Questionary questionary = new Questionary();
		questionary.setExamDate(LocalDate.now());
		questionary.setTopic("Addizioni");
		questionary.addQuestion(question1);
		questionary.addQuestion(question2);
		questionary.addQuestion(question3);

		//i punti totali possibili sono la somma dei punti di tutte le question
		assertEquals(12, questionary.getTotalPoints());

		//i punti fatti sono la somma dei punti delle domande corrette
		assertEquals(7, questionary.getCorrectPoints());

		//il voto in decimi è la traduzione del voto ottenuto in base 10
		//in questo caso sono 7 punti su 12    7/12 * 10 -> 5.83
		//voglio che sia arrotondato al mezzo punto più vicino (5.83 fa 6)
		//nel caso sia .25 o .75 esatto, siamo buoni, arrotondate per eccesso
		assertEquals(6.0, questionary.getVote());

		//ora cambio il punteggio della prima domanda
		question1.setPoints(6);
		//7/13 punti totali -> 5.38, deve fare 5.5
		assertEquals(5.5, questionary.getVote());


		//altro questionario
		Question question4 = new Question();
		question4.setText("Ti piace CSS?");
		question4.setPoints(5);
		question4.setAnswer("NO");
		question4.setCorrect(true);


		Question question5 = new Question();
		question5.setText("quanto fa true+true in js");
		question5.setPoints(4);
		question5.setAnswer("fa 2");
		question5.setCorrect(true);

		Question question6 = new Question();
		question6.setText("Devi leggere gli errori carattere per carattere?");
		question6.setPoints(3);
		question6.setAnswer("NO");
		question6.setCorrect(false);

		Questionary questionary2 = new Questionary();
		questionary2.setExamDate(LocalDate.now());
		questionary2.setTopic("Programmazione");
		questionary2.addQuestion(question4);
		questionary2.addQuestion(question5);
		questionary2.addQuestion(question6);

		assertEquals(7.5, questionary2.getVote());

		Student s = new Student();
		s.setName("Gina");
		s.setSurname("Manna");
		s.addQuestionary(questionary);
		assertEquals(1, s.getQuestionaries().size());

		try
		{
			//provo ad aggiungere lo stesso questionario
			s.addQuestionary(questionary);
			fail();
		} catch (QuestionaryException e)
		{
			assertEquals("questionario già presente",e.getMessage());
			assertEquals(1, s.getQuestionaries().size());
		}

		s.addQuestionary(questionary2);
		assertEquals(2, s.getQuestionaries().size());

		//ora voglio ottenere la media dello studente, calcolata come la media dei voti di tutti i suoi questionari, arrotondata come sopra
		assertEquals(6.0,s.getAverage());
	}

	@Test
	void fullTest()
	{
		Question question1 = new Question();
		question1.setText("Quanto fa 1+1");
		question1.setPoints(5);
		question1.setAnswer("fa 17");
		question1.setCorrect(false);


		Question question2 = new Question();
		question2.setText("Quanto fa 1+2");
		question2.setPoints(4);
		question2.setAnswer("fa 3");
		question2.setCorrect(true);

		Question question3 = new Question();
		question3.setText("Quanto fa 3+7");
		question3.setPoints(3);
		question3.setAnswer("fa 10");
		question3.setCorrect(true);

		Questionary questionary = new Questionary();
		questionary.setExamDate(LocalDate.now());
		questionary.setTopic("Addizioni");
		questionary.addQuestion(question1);
		questionary.addQuestion(question2);
		questionary.addQuestion(question3);

		//i punti totali possibili sono la somma dei punti di tutte le question
		assertEquals(12, questionary.getTotalPoints());

		//i punti fatti sono la somma dei punti delle domande corrette
		assertEquals(7, questionary.getCorrectPoints());

		//il voto in decimi è la traduzione del voto ottenuto in base 10
		//in questo caso sono 7 punti su 12    7/12 * 10 -> 5.83
		//voglio che sia arrotondato al mezzo punto più vicino (5.83 fa 6)
		//nel caso sia .25 o .75 esatto, siamo buoni, arrotondate per eccesso
		assertEquals(6.0, questionary.getVote());

		//ora cambio il punteggio della prima domanda
		question1.setPoints(6);
		//7/13 punti totali -> 5.38, deve fare 5.5
		assertEquals(5.5, questionary.getVote());


		//altro questionario
		Question question4 = new Question();
		question4.setText("Ti piace CSS?");
		question4.setPoints(5);
		question4.setAnswer("NO");
		question4.setCorrect(true);


		Question question5 = new Question();
		question5.setText("quanto fa true+true in js");
		question5.setPoints(4);
		question5.setAnswer("fa 2");
		question5.setCorrect(true);

		Question question6 = new Question();
		question6.setText("Devi leggere gli errori carattere per carattere?");
		question6.setPoints(3);
		question6.setAnswer("NO");
		question6.setCorrect(false);

		Questionary questionary2 = new Questionary();
		questionary2.setExamDate(LocalDate.now());
		questionary2.setTopic("Programmazione");
		questionary2.addQuestion(question4);
		questionary2.addQuestion(question5);
		questionary2.addQuestion(question6);

		assertEquals(7.5, questionary2.getVote());

		Student s = new Student();
		s.setName("Gina");
		s.setSurname("Manna");
		s.addQuestionary(questionary);
		assertEquals(1, s.getQuestionaries().size());

		try
		{
			//provo ad aggiungere lo stesso questionario
			s.addQuestionary(questionary);
			fail();
		} catch (QuestionaryException e)
		{
			assertEquals("questionario già presente",e.getMessage());
			assertEquals(1, s.getQuestionaries().size());
		}

		s.addQuestionary(questionary2);
		assertEquals(2, s.getQuestionaries().size());

		//ora voglio ottenere la media dello studente, calcolata come la media dei voti di tutti i suoi questionari, arrotondata come sopra
		assertEquals(6.0,s.getAverage());

		//ora provo a salvarli nel db, per vedere se funziona tutto
		questionRepository.save(question1);
		questionRepository.save(question2);
		questionRepository.save(question3);
		questionRepository.save(question4);
		questionRepository.save(question5);
		questionRepository.save(question6);

		questionaryRepository.save(questionary);
		questionaryRepository.save(questionary2);

		studentRepository.save(s);
	}

}
