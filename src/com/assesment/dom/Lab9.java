package com.assesment.dom;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Lab9 {

	public static void main(String[] args) {
		try {
			System.out.println("Programe Start");
			ReteriveQuestion reteriveQuestion =new ReteriveQuestion();
			List<AssesmentDO>custlist=reteriveQuestion.getAllQuestion();

			Document doc=DOMUtil.createDocument();
			
			Element quizTest=doc.createElement("quiz");
			doc.appendChild(quizTest);
			
			Element title=DOMUtil.createMyElement(doc, "title", "AVPN");
			quizTest.appendChild(title);
			
			Element totalQuestions=DOMUtil.createMyElement(doc, "totalQuizQuestions", "10");
			quizTest.appendChild(totalQuestions);
			
			Element testDuration=DOMUtil.createMyElement(doc, "quizDuration", "2");
			quizTest.appendChild(testDuration);
			
			Element root=doc.createElement("questions");
			quizTest.appendChild(root);


			for (AssesmentDO cto : custlist) {
				Element cust=doc.createElement("question");


				Element quele=DOMUtil.createMyElement(doc, "quizquestion", cto.getQuestion());
				cust.appendChild(quele);

				Element ansOneEle=DOMUtil.createMyElement(doc, "answer", cto.getOptOne());
				cust.appendChild(ansOneEle);

				Element ansTwoEle=DOMUtil.createMyElement(doc, "answer", cto.getOptTwo());
				cust.appendChild(ansTwoEle);

				Element ansThreeEle=DOMUtil.createMyElement(doc, "answer", cto.getOptThree());
				cust.appendChild(ansThreeEle);

				Element ansFourEle=DOMUtil.createMyElement(doc, "answer", cto.getOptFour());
				cust.appendChild(ansFourEle);

				Element correctEle=DOMUtil.createMyElement(doc, "correct", cto.getCorrAnswer());
				cust.appendChild(correctEle);

				root.appendChild(cust);


			}
			DOMUtil.printTOFile(doc, "mycust.xml");



		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Programe END");

	}

}

