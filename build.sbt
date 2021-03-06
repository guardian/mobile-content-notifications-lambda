name := "mobile-notifications-content"

organization := "com.gu"

description:= "lambda to replace the content-notifications-service"

version := "1.0"

scalaVersion := "2.12.8"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-target:jvm-1.8",
  "-Ywarn-dead-code"
)

resolvers ++= Seq(
   Resolver.sonatypeRepo("releases"),
  "Guardian Mobile Bintray" at "https://dl.bintray.com/guardian/mobile",
  "Guardian Platform Bintray" at "https://dl.bintray.com/guardian/platforms"
)

assemblyMergeStrategy in assembly := {
  case "META-INF/MANIFEST.MF" => MergeStrategy.discard
  case _ => MergeStrategy.first
}

val awsSdkVersion = "1.11.772"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-sts" % awsSdkVersion,
  "com.amazonaws" % "amazon-kinesis-client" % "1.7.6",
  "com.amazonaws" % "aws-lambda-java-core" % "1.2.1",
  "com.amazonaws" % "aws-lambda-java-events" % "2.2.8",
  "com.amazonaws" % "aws-java-sdk-cloudwatch" % awsSdkVersion,
  "com.amazonaws" % "aws-java-sdk-dynamodb" % awsSdkVersion,
  "com.gu" %% "content-api-client-default" % "14.3",
  "com.gu" %% "mobile-notifications-api-models" % "1.0.3",
  "com.gu" %% "thrift-serializer" % "4.0.0",
  "org.joda" % "joda-convert" % "1.8.1",
  "org.jsoup" % "jsoup" % "1.8.3",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.slf4j" % "slf4j-simple" % "1.7.25",
  "com.typesafe.akka" %% "akka-actor" % "2.5.2",
  "com.squareup.okhttp3" % "okhttp" % "3.14.8",
  "com.gu" %% "simple-configuration-ssm" % "1.5.2",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.mockito" % "mockito-all" % "1.9.0" % "test",
  "org.specs2" %% "specs2-core" % "4.5.1" % "test",
  "org.specs2" %% "specs2-matcher-extra" % "4.5.1" % "test"
)

enablePlugins(RiffRaffArtifact)

assemblyJarName := s"${name.value}.jar"
riffRaffPackageType := assembly.value
riffRaffUploadArtifactBucket := Option("riffraff-artifact")
riffRaffUploadManifestBucket := Option("riffraff-builds")
riffRaffManifestProjectName := s"Mobile::${name.value}"
riffRaffArtifactResources += (file("cfn.yaml"), s"${name.value}-cfn/cfn.yaml")
