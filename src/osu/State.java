//package osu;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class State {
//    private int budget;
//    private int resources;
//    private List<StateStep> stateSteps;
//
//    public State(int budget) {
//        this.budget = budget;
//        this.resources = 0;
//        this.stateSteps = new ArrayList<>();
//    }
//
//    public State(int budget, int resources) {
//        this.budget = budget;
//        this.resources = resources;
//        this.stateSteps = new ArrayList<>();
//    }
//
//    public int getBudget() {
//        return budget;
//    }
//
//    public void setBudget(int budget) {
//        this.budget = budget;
//    }
//
//    public int getResources() {
//        return resources;
//    }
//
//    public void setResources(int resources) {
//        this.resources = resources;
//    }
//
//    public void addStep(int time, Edge edge, Node node) {
//        stateSteps.add(new StateStep(time, edge, node));
//    }
//
//    public List<StateStep> getStateSteps() {
//        return stateSteps;
//    }
//
//    public void decreaseBudget(int cost) {
//        if (budget >= cost) {
//            this.budget -= cost;
//        } else {
//            throw new IllegalStateException("Not enough budget to tunnel.");
//        }
//    }
//
//    public void addResources(int additionalResources) {
//        this.resources += additionalResources;
//    }
//}