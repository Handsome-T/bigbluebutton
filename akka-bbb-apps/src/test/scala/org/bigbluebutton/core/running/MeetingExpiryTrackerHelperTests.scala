package org.bigbluebutton.core.running

import org.bigbluebutton.core.UnitSpec
import org.bigbluebutton.core.domain.MeetingInactivityTracker
import org.bigbluebutton.core.util.TimeUtil

class MeetingExpiryTrackerHelperTests extends UnitSpec {

  "A MeetingInactivityTrackerHelper" should "be return meeting is inactive" in {
    val nowInMinutes = TimeUtil.minutesToSeconds(15)

    val inactivityTracker = new MeetingInactivityTracker(
      maxInactivityTimeoutMinutes = 12,
      warningMinutesBeforeMax = 2,
      lastActivityTimestamp = TimeUtil.minutesToSeconds(5),
      warningSent = true,
      warningSentOnTimestamp = 0L
    )

    val active = inactivityTracker.isMeetingInactive(nowInMinutes)
    assert(active == false)
  }

  "A MeetingInactivityTrackerHelper" should "be return meeting is active" in {
    val nowInMinutes = TimeUtil.minutesToSeconds(18)
    val inactivityTracker = new MeetingInactivityTracker(
      maxInactivityTimeoutMinutes = 12,
      warningMinutesBeforeMax = 2,
      lastActivityTimestamp = TimeUtil.minutesToSeconds(5),
      warningSent = true,
      warningSentOnTimestamp = 0L
    )

    val inactive = inactivityTracker.isMeetingInactive(nowInMinutes)
    assert(inactive)
  }

}
