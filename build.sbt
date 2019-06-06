organization in ThisBuild := "com.thoughtworks.sbt"

val Delombok = project

val DelombokJavadoc = project.dependsOn(Delombok)

val DelombokJavaUnidoc = project.dependsOn(Delombok)
