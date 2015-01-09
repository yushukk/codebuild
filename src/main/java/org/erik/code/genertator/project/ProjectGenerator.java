package org.erik.code.genertator.project;

import org.apache.velocity.VelocityContext;
import org.erik.code.model.Project;

/**
 * Created by wandong.cwd on 2014/11/4.
 */
public interface ProjectGenerator {

    public void doGenerate(Project project, VelocityContext context);

}
