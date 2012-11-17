s = Server.local.boot;
s.quit;
Help.gui;


{SquareOsc.ar(350,0,0.5)}.play;
{SinOsc.ar(350,0,0.5)+SinOsc.ar(440,0,0.5)}.play;
{SinOsc.ar(440,0,0.5)+SinOsc.ar(480,0,0.5)}.play;
{SinOsc.ar(480,0,0.5)+SinOsc.ar(620,0,0.5)}.play;

(3>1).postln
a = (SinOsc.kr(2,0,1.0) < 0.0)
{SinOsc.ar(480,a.value,0.5)}.play;
a.value

a = (SinOsc.kr(2,0,1.0).value < 0.0)
b=400;
q =0.2;
	d = Duty.kr( 0.5, 0, Dseq(Array.series(13,1,0) ,inf));
	d.value;
(
	var squ;

	squ = Array.series(13,1,2);
	a = Array.series(13,1,0);
	b = midicps(Array.series(13,0,1) + 69);

	c = Duty.kr( 0.5, 0, Dseq(b ,inf));
	d = Duty.kr( 0.5, 0, Dseq(a ,inf));
	
	{Splay.ar(
		Mix.ar(
//			SinOsc.ar(440*squ,0,0.5/squ)
//			SinOsc.ar(c*squ,0,(0.5/squ) * d )
			SinOsc.ar(c*squ,0,0.5)
			),
		1, 0.5, 0)}.scope;
)
a = [1,2,3]
b = [1,2,4]
a*b
Array.series(10,1,0)
({
	var squ;
	var temp;
	squ = [1,3,5,7,9];
	temp = squ/3;
	
	squ.postln;
})

a = [1,2,3]
b = a * a
a
a.postln
a/3
SinOsc.ar(5*b,0,q/5),
SinOsc.ar(7*b,0,q/7),
SinOsc.ar(9*b,0,q/9),
SinOsc.ar(11*b,0,q/11)

(
a = Line.kr(0.0, 20.0, 4.0);
{
	Splay.ar( Mix([SinOsc.ar(400,0,0.4)]), 0, 0.5, Line.kr(-1,1,5))
}.scope
)

(
	{
		Splay.ar(
			Mix.ar([
				SinOsc.ar(MouseX.kr(220,440,0.01),0,MouseButton.kr(0.5,0,0.01)),
				SinOsc.ar(MouseY.kr(293, 587,0.01),0,MouseButton.kr(0,0.5,0.01))
					]),
			1,0.5)
	}.play;
)

{SinOsc.ar(261, 0, 0.5)}.play
{SinOsc.ar(Line.kr(261,392,25), 0, 0.5)}.play

(
	{
		Splay.ar(
			Mix.ar( [
				SinOsc.ar(MouseX.kr(261, 392,0.01),0,0.2),
				SinOsc.ar(MouseY.kr(392, 597,1),0,0.2)
			]), 1, 0.3, 0)
	}.play
)
(
	{
		var pan;
		pan = Line.kr(-1,1,5);
	
		Splay.ar( Mix([SinOsc.ar(400,0,0.4)]), 0, 0.5, pan.value)
	}.play;
)

(
	{
		var onOff;

		onOff = Duty.kr( 0.5, 0, Dseq([0,1],inf));

		Mix.ar( [SinOsc.ar(480, 0, onOff), SinOsc.ar(620, 0, onOff)])
	}.play
)
