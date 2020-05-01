package org.bytedeco.sbt.javacv

import sbt._
import sbt.Keys._
import org.bytedeco.sbt.javacpp.{ Plugin => JavaCppPlugin }

/**
 * Created by Lloyd on 2/22/16.
 */
object Plugin extends AutoPlugin {

  override def projectSettings: Seq[Setting[_]] = {
    import autoImport._
    Seq(
      javaCVVersion := Versions.javaCVVersion,
      libraryDependencies += {
        "org.bytedeco" % "javacv" % javaCVVersion.value excludeAll (
          ExclusionRule(organization = "org.bytedeco.javacpp-presets"),
          ExclusionRule(organization = "org.bytedeco.javacpp"))
      },
      JavaCppPlugin.autoImport.javaCppPresetLibs ++= JavaCVCppPresets.libs)
  }

  object Versions {
    val javaCVVersion = "1.5.3"
  }

  object autoImport {
    val javaCVVersion = SettingKey[String]("javaCVVersion", s"Version of Java CV that you want to use, defaults to ${Versions.javaCVVersion}")
  }

  object JavaCVCppPresets {
    /**
     * List of default JavaCPP preset names and versions that will be added by this plugin.
     * From pom.xml of JavaCV.
     */
    val libs = Seq(
      "openblas" -> "0.3.9",
      "opencv" -> "4.3.0",
      "ffmpeg" -> "4.2.2",
      "flycapture" -> "2.13.3.31",
      "libdc1394" -> "2.2.6",
      "libfreenect" -> "0.5.7",
      "libfreenect2" -> "0.2.0",
      "librealsense" -> "1.12.4",
      "librealsense2" -> "2.29.0",
      "videoinput" -> "0.200",
      "artoolkitplus" -> "2.3.1",
      "flandmark" -> "1.07",
      "leptonica" -> "1.79.0",
      "tesseract" -> "4.1.1")
  }

  override def requires: Plugins = plugins.JvmPlugin && JavaCppPlugin

  override def trigger: PluginTrigger = allRequirements

}
