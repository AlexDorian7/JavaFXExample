package team.logica_populi.javafxdemo.xml.tags;

import org.jetbrains.annotations.Nullable;
import team.logica_populi.javafxdemo.xml.properties.StringProperty;

public class Question extends XMLTreeNode {

    public final StringProperty question = new StringProperty("Question", "What is question?");
    public final StringProperty answer1 = new StringProperty("Answer1", "Answer 1");
    public final StringProperty answer2 = new StringProperty("Answer2", "Answer 2");
    public final StringProperty answer3 = new StringProperty("Answer3", "Answer 3");
    public final StringProperty answer4 = new StringProperty("Answer4", "Answer 4");

    private AnswerOptions correct = AnswerOptions.ANSWER1;
    @Nullable
    private AnswerOptions selected = null;

    public Question(XMLTreeNode parent) {
        super("Question", parent);
        addProperties(question, answer1, answer2, answer3, answer4);
    }

    public Question() {
        this(null);
    }

    public void setData(String q, String a1, String a2, String a3, String a4, AnswerOptions correct) {
        question.setValue(q);
        answer1.setValue(a1);
        answer2.setValue(a2);
        answer3.setValue(a3);
        answer4.setValue(a4);
        this.correct = correct;
    }

    public AnswerOptions getCorrect() {
        return correct;
    }

    @Nullable
    public AnswerOptions getSelected() {
        return selected;
    }

    public void setSelected(@Nullable AnswerOptions selected) {
        this.selected = selected;
    }
}

