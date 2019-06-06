package com.thoughtworks.sbt

import com.thoughtworks.sbt.Delombok.autoImport.delombok
import sbt.Keys._
import sbt._
import sbtunidoc.BaseUnidocPlugin.autoImport._
import sbtunidoc.JavaUnidocPlugin
import sbtunidoc.JavaUnidocPlugin.autoImport._

object DelombokJavaunidoc extends AutoPlugin {
  lazy val allDelombokTask = Def.taskDyn {
    val f = (unidocScopeFilter in unidoc in JavaUnidoc).value
    delombok.all(f)
  }

  override def trigger = allRequirements

  override def requires: Plugins = JavaUnidocPlugin

  override def projectSettings: Seq[Def.Setting[_]] =
//    Seq(Compile /*, Test*/).flatMap(
//      inConfig(_)(
          Seq(
            sources in unidoc := {
              val f = (unidocScopeFilter in unidoc).value
              val delombokMap = allDelombokTask.value.reduce(_ ++ _)
              (sources in unidoc).value.map { sourceFile: File =>
                delombokMap.getOrElse(sourceFile, sourceFile)
              }
            }
//        )
//      )

    )
}
