package com.test.maps.service

import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object MapsServiceTest:Spek ({
  val mapsService by memoized { MapsService() }

  describe("get the id for city") {
    it("1 + 2 == 3") {
      Assert.assertEquals("", mapsService.findPlace("Chennai"))
    }
  }
})