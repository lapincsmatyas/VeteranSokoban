package visitor_pattern;

import push_enums.Direction;
import push_enums.StepResult;

/**
 * A visitor_pattern patternhez szukseges visitable interface
 */
public interface Visitable {
    StepResult accept(Visitor visitor, Direction dir, int force);
}
