

package ArchConfig;

import chisel3._


trait ArchConfig {
  val xLen: Int
  val regFileSize: Int
  val lifetime_check_strict: Boolean
  def regAddrWidth: Int = (Math.log(this.regFileSize) / Math.log(2)).asInstanceOf
  def assert: Boolean
}

object VLiwXArch extends ArchConfig {
  val xLen = 64
  val regFileSize = 128
  val lifetime_check_strict = true
  def assert = this.regAddrWidth == 7
}
