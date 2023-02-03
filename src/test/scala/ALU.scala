

import chisel3._
import chiseltest._
import org.scalatest.freespec.AnyFreeSpec
import chisel3.experimental.BundleLiterals._

import ALU._
import ArchConfig._


class ALUTest extends AnyFreeSpec with ChiselScalatestTester {
  // test class body here
  test (new ALU(VLiwXArch)) { alu =>
    
  }
}