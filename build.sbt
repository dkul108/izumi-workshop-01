import com.github.pshirshov.izumi.sbt.deps.IzumiDeps

name := "distage-workshop"

version := "0.1"

scalaVersion := "2.12.6"

organization in ThisBuild := "com.github.pshirshov.izumi.workshop.w01"


enablePlugins(IzumiGitEnvironmentPlugin)

lazy val AppSettings = new SettingsGroup {
  override val settings: Seq[sbt.Setting[_]] = Seq(
    libraryDependencies ++= Seq(Izumi.R.distage_roles),
  )
}

lazy val RoleSettings = new SettingsGroup {
  override val settings: Seq[sbt.Setting[_]] = Seq(
    libraryDependencies ++= Seq(Izumi.R.distage_roles_api),
  )
}

lazy val inRoot = In(".")

lazy val inRoles = In("role").settings(RoleSettings)

lazy val inApp = In("app").settings(AppSettings)


lazy val usersRole = inRoles.as.module

lazy val accountsRole = inRoles.as.module

lazy val launcher = inApp.as.module
  .depends(usersRole, accountsRole)

lazy val workshop = inRoot.as.root
  .transitiveAggregate(launcher)

/*
At this point use thse commands to setup project layout from sbt shell:

newModule role/accounts-role
newModule role/users-role
newModule app/launcher
*/
