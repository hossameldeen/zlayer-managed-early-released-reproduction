name := "zlayer-managed-early-release-reproduction"

version := "0.1"

scalaVersion := "2.13.3"

val zioVersion                    = "1.0.3"
val zioConfigVersion              = "1.0.0-RC30-1"
val zioInteropCatsVersion         = "2.2.0.1"
val zioLoggingVersion             = "0.5.4"
val logbackVersion                = "1.2.3"
val logstashLogbackEncoderVersion = "6.5"
val skunkVersion                  = "0.0.21"
val calibanVersion                = "0.9.2"
val sttpVersion                   = "3.0.0-RC11"
val fs2AwsVersion                 = "3.0.2"
val fuuidVersion                  = "0.4.0"
val circeVersion                  = "0.13.0"
val jawnVersion                   = "1.0.0"
val gatlingVersion                = "3.5.1"
val http4sVersion                 = "0.21.18"

libraryDependencies ++= Seq(
  // ZIO
  "dev.zio" %% "zio-config"          % zioConfigVersion,
  "dev.zio" %% "zio-config-typesafe" % zioConfigVersion,
  "dev.zio" %% "zio-interop-cats"    % zioInteropCatsVersion,
  "dev.zio" %% "zio-logging-slf4j"   % zioLoggingVersion,
  "dev.zio" %% "zio-test"            % zioVersion % "test",
  "dev.zio" %% "zio-test-sbt"        % zioVersion % "test",
  // Logging backend
  "ch.qos.logback"       % "logback-classic"          % logbackVersion,
  "net.logstash.logback" % "logstash-logback-encoder" % logstashLogbackEncoderVersion,
  // Skunk
  "org.tpolecat" %% "skunk-core"  % skunkVersion,
  "org.tpolecat" %% "skunk-circe" % skunkVersion,
  // Caliban
  "com.github.ghostdogpr" %% "caliban"        % calibanVersion,
  "com.github.ghostdogpr" %% "caliban-http4s" % calibanVersion,
  "com.github.ghostdogpr" %% "caliban-client" % calibanVersion,
  // Sttp
  "com.softwaremill.sttp.client3" %% "core"                          % sttpVersion,
  "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % sttpVersion,
  "com.softwaremill.sttp.client3" %% "circe"                         % sttpVersion,
  // FS2-AWS
  "io.laserdisc" %% "fs2-aws"    % fs2AwsVersion,
  "io.laserdisc" %% "fs2-aws-s3" % fs2AwsVersion,
  // FUUID
  "io.chrisdavenport" %% "fuuid"       % fuuidVersion,
  "io.chrisdavenport" %% "fuuid-circe" % fuuidVersion,
  // Circe
  "io.circe"      %% "circe-core"    % circeVersion,
  "io.circe"      %% "circe-generic" % circeVersion,
  "io.circe"      %% "circe-parser"  % circeVersion,
  "io.circe"      %% "circe-literal" % circeVersion,
  "org.typelevel" %% "jawn-parser"   % jawnVersion,
  // Gatling
  "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test",
  "io.gatling"            % "gatling-test-framework"    % gatlingVersion % "test",
  // Http4s
  "org.http4s" %% "http4s-blaze-server"       % http4sVersion,
  "org.http4s" %% "http4s-prometheus-metrics" % http4sVersion
)

