Title: Time Saved
Ever wonder exactly how much time you might be saving being able to quickly
compile and test code with OpenEJB?  Well, find out!

Deploy times in Java EE in general are partially based on the platform and
partially on the size of the application itself.  With that in mind, we've
put together this little calculator so you can see what OpenEJB is doing
for you!

<a name="TimeSaved-Calculator"></a>
# Calculator
<script type="text/javascript" src="http://prototypejs.org/assets/2010/4/1/prototype.js"></script>
<script>

  function calculate() {
    var developers = $("devs").getValue();
    var cycles = $("cycles").getValue();
    var weeks = $("weeks").getValue();

    var individual_deployTime_openejb = $("time_openejb").getValue();
    var individual_deployTime_vendor = $("time_vendor").getValue();
    var individual_deployTime_savings = individual_deployTime_vendor - individual_deployTime_openejb;

    var individual_deployTimePerHour_openejb = cycles * individual_deployTime_openejb / 8;
    var individual_deployTimePerHour_vendor =  cycles * individual_deployTime_vendor / 8;
    var individual_deployTimePerHour_savings = individual_deployTimePerHour_vendor - individual_deployTimePerHour_openejb;

    var individual_deployTimePerWeek_openejb = individual_deployTimePerHour_openejb * 40;
    var individual_deployTimePerWeek_vendor = individual_deployTimePerHour_vendor * 40;
    var individual_deployTimePerWeek_savings = individual_deployTimePerWeek_vendor - individual_deployTimePerWeek_openejb;

    var individual_deployTimePerMonth_openejb = individual_deployTimePerWeek_openejb * 4.33;
    var individual_deployTimePerMonth_vendor = individual_deployTimePerWeek_vendor * 4.33;
    var individual_deployTimePerMonth_savings = individual_deployTimePerMonth_vendor - individual_deployTimePerMonth_openejb;

    var team_deployTime_openejb = developers * individual_deployTime_openejb;
    var team_deployTime_vendor = developers * individual_deployTime_vendor;
    var team_deployTime_savings = developers * individual_deployTime_savings;

    var team_deployTimePerHour_openejb = developers * individual_deployTimePerHour_openejb;
    var team_deployTimePerHour_vendor = developers * individual_deployTimePerHour_vendor;
    var team_deployTimePerHour_savings = developers * individual_deployTimePerHour_savings;

    var team_deployTimePerWeek_openejb = developers * individual_deployTimePerWeek_openejb;
    var team_deployTimePerWeek_vendor = developers * individual_deployTimePerWeek_vendor;
    var team_deployTimePerWeek_savings = developers * individual_deployTimePerWeek_savings;

    var team_deployTimePerMonth_openejb = developers * individual_deployTimePerMonth_openejb;
    var team_deployTimePerMonth_vendor = developers * individual_deployTimePerMonth_vendor;
    var team_deployTimePerMonth_savings = developers * individual_deployTimePerMonth_savings;

    var total_savings = team_deployTimePerWeek_savings * weeks;

    $("individual.deployTime.openejb").innerHTML = format(individual_deployTime_openejb);
    $("individual.deployTime.vendor").innerHTML = format(individual_deployTime_vendor);
    $("individual.deployTime.savings").innerHTML = format(individual_deployTime_savings);

    $("individual.deployTimePerHour.openejb").innerHTML = format(individual_deployTimePerHour_openejb);
    $("individual.deployTimePerHour.vendor").innerHTML = format(individual_deployTimePerHour_vendor);
    $("individual.deployTimePerHour.savings").innerHTML = format(individual_deployTimePerHour_savings);

    $("individual.deployTimePerWeek.openejb").innerHTML = format(individual_deployTimePerWeek_openejb);
    $("individual.deployTimePerWeek.vendor").innerHTML = format(individual_deployTimePerWeek_vendor);
    $("individual.deployTimePerWeek.savings").innerHTML = format(individual_deployTimePerWeek_savings);

    $("individual.deployTimePerMonth.openejb").innerHTML = format(individual_deployTimePerMonth_openejb);
    $("individual.deployTimePerMonth.vendor").innerHTML = format(individual_deployTimePerMonth_vendor);
    $("individual.deployTimePerMonth.savings").innerHTML = format(individual_deployTimePerMonth_savings);

    $("team.deployTime.openejb").innerHTML = format(team_deployTime_openejb);
    $("team.deployTime.vendor").innerHTML = format(team_deployTime_vendor);
    $("team.deployTime.savings").innerHTML = format(team_deployTime_savings);
    $("team.deployTimePerHour.openejb").innerHTML = format(team_deployTimePerHour_openejb);
    $("team.deployTimePerHour.vendor").innerHTML = format(team_deployTimePerHour_vendor);
    $("team.deployTimePerHour.savings").innerHTML = format(team_deployTimePerHour_savings);
    $("team.deployTimePerWeek.openejb").innerHTML = format(team_deployTimePerWeek_openejb);
    $("team.deployTimePerWeek.vendor").innerHTML = format(team_deployTimePerWeek_vendor);
    $("team.deployTimePerWeek.savings").innerHTML = format(team_deployTimePerWeek_savings);
    $("team.deployTimePerMonth.openejb").innerHTML = format(team_deployTimePerMonth_openejb);
    $("team.deployTimePerMonth.vendor").innerHTML = format(team_deployTimePerMonth_vendor);
    $("team.deployTimePerMonth.savings").innerHTML = format(team_deployTimePerMonth_savings);

    $("totalSavings").innerHTML = longFormat(total_savings);

  }

  function format(secs) {
    var mins = Math.floor(secs / 60)
    secs = secs % 60;
    secs -= secs % 1;

    var hrs = Math.floor(mins / 60)
    mins = mins % 60

    if (secs < 10) secs = "0" + secs;
    if (mins < 10) mins = "0" + mins;
    if (hrs < 10) hrs = "0" + hrs;

    return hrs + ":" + mins + ":" + secs + "";
  }

  function longFormat(secs) {
    var mins = Math.floor(secs / 60)
    secs = secs % 60;
    secs -= secs % 1;

    var hrs = Math.floor(mins / 60)
    mins = mins % 60

    // eight hours per work day
    var days = Math.floor(hrs / 8)
    hrs = hrs % 8

    // five days per work week
    var weeks = Math.floor(days / 5)
    days = days % 5

    var str = "";
    if (weeks > 0) str = weeks + " weeks, "
    if (str.length > 0 || days > 0) str += days + " days, "
    if (str.length > 0 || hrs > 0) str += hrs + " hours, "
    if (str.length > 0 || mins > 0) str += mins + " minutes, "
    if (secs > 0) str += secs + " seconds"

    str = str.replace(/, $/, "");
    str = str.replace(/^ */, "");

    return str;
  }
</script>

<table>
  <col width=370>
    <!--<col width=200>-->
  <tr>
    <td><strong>How many developers?</strong></td>
    <td><input id="devs" type="text" value="0" size="5"/></td>
  </tr>
  <tr>
    <td><strong>How many weeks?</strong>
    </td>
    <td><input id="weeks" type="text" value="0" size="5"/></td>
  </tr>
  <tr>
    <td><strong>How many times do you compile/run per day?</strong></td>
    <td><input id="cycles" type="text" value="0" size="5"/></td>
  </tr>
  <tr>
    <td><strong>Time to start/deploy your app in OpenEJB?</strong></td>
    <td><input id="time_openejb" value="0" type="text" size="5"/> <i>seconds</i></td>
  </tr>
  <tr>
    <td><strong>Time to start/deploy your app in your other platform?</strong></td>
    <td><input id="time_vendor" value="0" type="text" size="5"/> <i>seconds</i></td>
  </tr>
  <tr>
    <td colspan="2" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input type="button" id="calculate" value="Calculate" onclick="calculate();"/></td>
  </tr>
</table>


<h3>Individual Stats</h3>
<table cellpadding="1" border="1" style='border-collapse:collapse'>
  <col width=198>
  <col width=90>
  <col width=90>
  <col width=90>
  <tr>
    <td></td>
    <td align="center">OpenEJB</td>
    <td align="center">Vendor</td>
    <td align="center"><b>Savings</b></td>
  </tr>
  <tr>
    <td>deployTime</td>
    <td align="center" id="individual.deployTime.openejb"></td>
    <td align="center" id="individual.deployTime.vendor"></td>
    <td align="center" id="individual.deployTime.savings"></td>
  </tr>
  <tr>
    <td>deployTimePerHour</td>
    <td align="center" id="individual.deployTimePerHour.openejb"></td>
    <td align="center" id="individual.deployTimePerHour.vendor"></td>
    <td align="center" id="individual.deployTimePerHour.savings"></td>
  </tr>
  <tr>
    <td>deployTimePerWeek</td>
    <td align="center" id="individual.deployTimePerWeek.openejb"></td>
    <td align="center" id="individual.deployTimePerWeek.vendor"></td>
    <td align="center" id="individual.deployTimePerWeek.savings"></td>
  </tr>
  <tr>
    <td>deployTimePerMonth</td>
    <td align="center" id="individual.deployTimePerMonth.openejb"></td>
    <td align="center" id="individual.deployTimePerMonth.vendor"></td>
    <td align="center" id="individual.deployTimePerMonth.savings"></td>
  </tr>
</table>
<p><i>Time in HH:MM:SS format</i></p>
<h3>Team Stats</h3>
<table border=1 cellpadding=2 cellspacing=2 style='border-collapse:
 collapse;table-layout:fixed'>
  <col width=198>
  <col width=90>
  <col width=90>
  <col width=90>
  <tr>
    <td></td>
    <td align="center">OpenEJB</td>
    <td align="center">Vendor</td>
    <td align="center"><b>Savings</b></td>
  </tr>

  <tr>
    <td>deployTime</td>
    <td align="center" id="team.deployTime.openejb"></td>
    <td align="center" id="team.deployTime.vendor"></td>
    <td align="center" id="team.deployTime.savings"></td>
  </tr>
  <tr>
    <td>deployTimePerHour</td>
    <td align="center" id="team.deployTimePerHour.openejb"></td>
    <td align="center" id="team.deployTimePerHour.vendor"></td>
    <td align="center" id="team.deployTimePerHour.savings"></td>
  </tr>
  <tr>
    <td>deployTimePerWeek</td>
    <td align="center" id="team.deployTimePerWeek.openejb"></td>
    <td align="center" id="team.deployTimePerWeek.vendor"></td>
    <td align="center" id="team.deployTimePerWeek.savings"></td>
  </tr>
  <tr>
    <td>deployTimePerMonth</td>
    <td align="center" id="team.deployTimePerMonth.openejb"></td>
    <td align="center" id="team.deployTimePerMonth.vendor"></td>
    <td align="center" id="team.deployTimePerMonth.savings"></td>
  </tr>
</table>

<h3>Total Savings</h3>

<p><span  id="totalSavings"></span></p>
