

import chisel3._
import chiseltest._
import org.scalatest.freespec.AnyFreeSpec
import chisel3.experimental.BundleLiterals._

import ArchConfig._
import ALU._
import RegFile._


class ALUTest extends AnyFreeSpec with ChiselScalatestTester {
  // test class body here
  test (new ALU(VLiwXArch)) { alu =>
    var regfile = new RegFile(VLiwXArch)
    alu.io.reg <> regfile.io
  }
}