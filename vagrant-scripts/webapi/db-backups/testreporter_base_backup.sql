--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.10
-- Dumped by pg_dump version 9.5.7

-- Started on 2017-12-03 16:25:18 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12361)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2193 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 34089)
-- Name: failure; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE failure (
    id integer NOT NULL,
    message character varying(255),
    stacktrace text
);


ALTER TABLE failure OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 34095)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 34097)
-- Name: nunit_property; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE nunit_property (
    id integer NOT NULL,
    name_name character varying(255),
    prop_value character varying(255)
);


ALTER TABLE nunit_property OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 34103)
-- Name: testcase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testcase (
    internalid integer NOT NULL,
    asserts integer,
    fullname character varying(255),
    id integer,
    name character varying(255),
    result character varying(255),
    "time" character varying(255),
    failure_id integer
);


ALTER TABLE testcase OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 34109)
-- Name: testcase_nunit_property; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testcase_nunit_property (
    testcase_internalid integer NOT NULL,
    properties_id integer NOT NULL
);


ALTER TABLE testcase_nunit_property OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 34112)
-- Name: testgroup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testgroup (
    id integer NOT NULL,
    description text,
    name character varying(255)
);


ALTER TABLE testgroup OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 34118)
-- Name: testrun; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testrun (
    id integer NOT NULL,
    asserts integer,
    failed integer,
    fullname character varying(255),
    inconclusive integer,
    name character varying(255),
    passed integer,
    result character varying(255),
    rundate character varying(255),
    skipped integer,
    starttime character varying(255),
    testcasecount character varying(255),
    "time" character varying(255),
    total integer,
    testgroup_id integer,
    testsuite_internalid integer
);


ALTER TABLE testrun OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 34124)
-- Name: testsuite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testsuite (
    internalid integer NOT NULL,
    asserts integer,
    failed integer,
    fullname character varying(255),
    id integer,
    inconclusive integer,
    name character varying(255),
    passed integer,
    result character varying(255),
    skipped integer,
    testcasecount character varying(255),
    "time" character varying(255),
    total integer,
    type character varying(255),
    failure_id integer
);


ALTER TABLE testsuite OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 34130)
-- Name: testsuite_nunit_property; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testsuite_nunit_property (
    testsuite_internalid integer NOT NULL,
    properties_id integer NOT NULL
);


ALTER TABLE testsuite_nunit_property OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 34133)
-- Name: testsuite_testcase; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testsuite_testcase (
    testsuite_internalid integer NOT NULL,
    testcases_internalid integer NOT NULL
);


ALTER TABLE testsuite_testcase OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 34136)
-- Name: testsuite_testsuite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE testsuite_testsuite (
    testsuite_internalid integer NOT NULL,
    testsuites_internalid integer NOT NULL
);


ALTER TABLE testsuite_testsuite OWNER TO postgres;

--
-- TOC entry 2175 (class 0 OID 34089)
-- Dependencies: 181
-- Data for Name: failure; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY failure (id, message, stacktrace) FROM stdin;
4	Child test failed	\N
8	Child test failed	\N
12	Intentional failure	   at NUnit.Framework.Assert.Fail(String message, Object[] args) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 142\n   at NUnit.Framework.Assert.Fail(String message) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 152\n   at NUnit.Tests.Assemblies.MockTestFixture.FailingTest() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 121
31	System.ApplicationException : Intentional Exception	   at NUnit.Tests.Assemblies.MockTestFixture.MethodThrowsException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 158\n   at NUnit.Tests.Assemblies.MockTestFixture.TestWithException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 153
56	Child test failed	\N
60	Child test failed	\N
64	Intentional failure	   at NUnit.Framework.Assert.Fail(String message, Object[] args) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 142\n   at NUnit.Framework.Assert.Fail(String message) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 152\n   at NUnit.Tests.Assemblies.MockTestFixture.FailingTest() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 121
83	System.ApplicationException : Intentional Exception	   at NUnit.Tests.Assemblies.MockTestFixture.MethodThrowsException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 158\n   at NUnit.Tests.Assemblies.MockTestFixture.TestWithException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 153
108	Child test failed	\N
112	Child test failed	\N
116	Intentional failure	   at NUnit.Framework.Assert.Fail(String message, Object[] args) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 142\n   at NUnit.Framework.Assert.Fail(String message) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 152\n   at NUnit.Tests.Assemblies.MockTestFixture.FailingTest() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 121
135	System.ApplicationException : Intentional Exception	   at NUnit.Tests.Assemblies.MockTestFixture.MethodThrowsException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 158\n   at NUnit.Tests.Assemblies.MockTestFixture.TestWithException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 153
160	Child test failed	\N
164	Child test failed	\N
168	Intentional failure	   at NUnit.Framework.Assert.Fail(String message, Object[] args) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 142\n   at NUnit.Framework.Assert.Fail(String message) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 152\n   at NUnit.Tests.Assemblies.MockTestFixture.FailingTest() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 121
187	System.ApplicationException : Intentional Exception	   at NUnit.Tests.Assemblies.MockTestFixture.MethodThrowsException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 158\n   at NUnit.Tests.Assemblies.MockTestFixture.TestWithException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 153
212	Child test failed	\N
216	Child test failed	\N
220	Intentional failure	   at NUnit.Framework.Assert.Fail(String message, Object[] args) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 142\n   at NUnit.Framework.Assert.Fail(String message) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 152\n   at NUnit.Tests.Assemblies.MockTestFixture.FailingTest() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 121
239	System.ApplicationException : Intentional Exception	   at NUnit.Tests.Assemblies.MockTestFixture.MethodThrowsException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 158\n   at NUnit.Tests.Assemblies.MockTestFixture.TestWithException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 153
265	Child test failed	\N
269	Child test failed	\N
273	Intentional failure	   at NUnit.Framework.Assert.Fail(String message, Object[] args) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 142\n   at NUnit.Framework.Assert.Fail(String message) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 152\n   at NUnit.Tests.Assemblies.MockTestFixture.FailingTest() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 121
292	System.ApplicationException : Intentional Exception	   at NUnit.Tests.Assemblies.MockTestFixture.MethodThrowsException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 158\n   at NUnit.Tests.Assemblies.MockTestFixture.TestWithException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 153
318	Child test failed	\N
322	Child test failed	\N
326	Intentional failure	   at NUnit.Framework.Assert.Fail(String message, Object[] args) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 142\n   at NUnit.Framework.Assert.Fail(String message) in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\framework\\Assert.cs:line 152\n   at NUnit.Tests.Assemblies.MockTestFixture.FailingTest() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 121
345	System.ApplicationException : Intentional Exception	   at NUnit.Tests.Assemblies.MockTestFixture.MethodThrowsException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 158\n   at NUnit.Tests.Assemblies.MockTestFixture.TestWithException() in D:\\Dev\\NUnit\\nunit-3.0\\work\\NUnitFramework\\src\\mock-assembly\\MockAssembly.cs:line 153
\.


--
-- TOC entry 2194 (class 0 OID 0)
-- Dependencies: 182
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 368, true);


--
-- TOC entry 2177 (class 0 OID 34097)
-- Dependencies: 183
-- Data for Name: nunit_property; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY nunit_property (id, name_name, prop_value) FROM stdin;
5	_PID	11928
6	_APPDOMAIN	test-domain-mock-assembly.dll
9	Category	FixtureCategory
10	Description	Fake Test Fixture
15	Description	Mock Test #1
17	Severity	Critical
18	Description	This is a really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really long description
19	Category	MockCategory
21	Category	AnotherCategory
22	Category	MockCategory
24	Category	Foo
25	_SKIPREASON	ignoring this test method for now
27	_SKIPREASON	Method is not public
29	_SKIPREASON	No arguments were provided
33	TargetMethod	SomeClassName
34	Size	5
36	_SKIPREASON	No suitable constructor was found
42	_SKIPREASON	
57	_PID	11928
58	_APPDOMAIN	test-domain-mock-assembly.dll
61	Category	FixtureCategory
62	Description	Fake Test Fixture
67	Description	Mock Test #1
69	Severity	Critical
70	Description	This is a really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really long description
71	Category	MockCategory
73	Category	AnotherCategory
74	Category	MockCategory
76	Category	Foo
77	_SKIPREASON	ignoring this test method for now
79	_SKIPREASON	Method is not public
81	_SKIPREASON	No arguments were provided
85	TargetMethod	SomeClassName
86	Size	5
88	_SKIPREASON	No suitable constructor was found
94	_SKIPREASON	
109	_PID	11928
110	_APPDOMAIN	test-domain-mock-assembly.dll
113	Category	FixtureCategory
114	Description	Fake Test Fixture
119	Description	Mock Test #1
121	Severity	Critical
122	Description	This is a really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really long description
123	Category	MockCategory
125	Category	AnotherCategory
126	Category	MockCategory
128	Category	Foo
129	_SKIPREASON	ignoring this test method for now
131	_SKIPREASON	Method is not public
133	_SKIPREASON	No arguments were provided
137	TargetMethod	SomeClassName
138	Size	5
140	_SKIPREASON	No suitable constructor was found
146	_SKIPREASON	
161	_PID	11928
162	_APPDOMAIN	test-domain-mock-assembly.dll
165	Category	FixtureCategory
166	Description	Fake Test Fixture
171	Description	Mock Test #1
173	Severity	Critical
174	Description	This is a really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really long description
175	Category	MockCategory
177	Category	AnotherCategory
178	Category	MockCategory
180	Category	Foo
181	_SKIPREASON	ignoring this test method for now
183	_SKIPREASON	Method is not public
185	_SKIPREASON	No arguments were provided
189	TargetMethod	SomeClassName
190	Size	5
192	_SKIPREASON	No suitable constructor was found
198	_SKIPREASON	
213	_PID	11928
214	_APPDOMAIN	test-domain-mock-assembly.dll
217	Category	FixtureCategory
218	Description	Fake Test Fixture
223	Description	Mock Test #1
225	Severity	Critical
226	Description	This is a really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really long description
227	Category	MockCategory
229	Category	AnotherCategory
230	Category	MockCategory
232	Category	Foo
233	_SKIPREASON	ignoring this test method for now
235	_SKIPREASON	Method is not public
237	_SKIPREASON	No arguments were provided
241	TargetMethod	SomeClassName
242	Size	5
244	_SKIPREASON	No suitable constructor was found
250	_SKIPREASON	
266	_PID	11928
267	_APPDOMAIN	test-domain-mock-assembly.dll
270	Category	FixtureCategory
271	Description	Fake Test Fixture
276	Description	Mock Test #1
278	Severity	Critical
279	Description	This is a really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really long description
280	Category	MockCategory
282	Category	AnotherCategory
283	Category	MockCategory
285	Category	Foo
286	_SKIPREASON	ignoring this test method for now
288	_SKIPREASON	Method is not public
290	_SKIPREASON	No arguments were provided
294	TargetMethod	SomeClassName
295	Size	5
297	_SKIPREASON	No suitable constructor was found
303	_SKIPREASON	
319	_PID	11928
320	_APPDOMAIN	test-domain-mock-assembly.dll
323	Category	FixtureCategory
324	Description	Fake Test Fixture
329	Description	Mock Test #1
331	Severity	Critical
332	Description	This is a really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really, really long description
333	Category	MockCategory
335	Category	AnotherCategory
336	Category	MockCategory
338	Category	Foo
339	_SKIPREASON	ignoring this test method for now
341	_SKIPREASON	Method is not public
343	_SKIPREASON	No arguments were provided
347	TargetMethod	SomeClassName
348	Size	5
350	_SKIPREASON	No suitable constructor was found
356	_SKIPREASON	
\.


--
-- TOC entry 2178 (class 0 OID 34103)
-- Dependencies: 184
-- Data for Name: testcase; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testcase (internalid, asserts, fullname, id, name, result, "time", failure_id) FROM stdin;
11	0	NUnit.Tests.Assemblies.MockTestFixture.FailingTest	1005	FailingTest	Failed	0.023	12
13	0	NUnit.Tests.Assemblies.MockTestFixture.InconclusiveTest	1010	InconclusiveTest	Inconclusive	0.001	\N
14	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest1	1001	MockTest1	Passed	0.000	\N
16	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest2	1002	MockTest2	Passed	0.000	\N
20	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest3	1003	MockTest3	Passed	0.000	\N
23	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest4	1007	MockTest4	Skipped	0.000	\N
26	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest5	1004	MockTest5	Skipped	0.000	\N
28	0	NUnit.Tests.Assemblies.MockTestFixture.NotRunnableTest	1009	NotRunnableTest	Skipped	0.000	\N
30	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithException	1011	TestWithException	Failed	0.002	31
32	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithManyProperties	1006	TestWithManyProperties	Passed	0.000	\N
39	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(2,2)	1027	MethodWithParameters(2,2)	Passed	0.006	\N
40	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(9,11)	1028	MethodWithParameters(9,11)	Passed	0.000	\N
45	0	NUnit.Tests.ParameterizedFixture(42).Test1	1031	Test1	Passed	0.000	\N
46	0	NUnit.Tests.ParameterizedFixture(42).Test2	1032	Test2	Passed	0.000	\N
48	0	NUnit.Tests.ParameterizedFixture(5).Test1	1034	Test1	Passed	0.000	\N
49	0	NUnit.Tests.ParameterizedFixture(5).Test2	1035	Test2	Passed	0.000	\N
51	0	NUnit.Tests.Singletons.OneTestCase.TestCase	1013	TestCase	Passed	0.000	\N
53	0	NUnit.Tests.TestAssembly.MockTestFixture.MyTest	1015	MyTest	Passed	0.001	\N
63	0	NUnit.Tests.Assemblies.MockTestFixture.FailingTest	1005	FailingTest	Failed	0.023	64
65	0	NUnit.Tests.Assemblies.MockTestFixture.InconclusiveTest	1010	InconclusiveTest	Inconclusive	0.001	\N
66	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest1	1001	MockTest1	Passed	0.000	\N
68	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest2	1002	MockTest2	Passed	0.000	\N
72	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest3	1003	MockTest3	Passed	0.000	\N
75	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest4	1007	MockTest4	Skipped	0.000	\N
78	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest5	1004	MockTest5	Skipped	0.000	\N
80	0	NUnit.Tests.Assemblies.MockTestFixture.NotRunnableTest	1009	NotRunnableTest	Skipped	0.000	\N
82	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithException	1011	TestWithException	Failed	0.002	83
84	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithManyProperties	1006	TestWithManyProperties	Passed	0.000	\N
91	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(2,2)	1027	MethodWithParameters(2,2)	Passed	0.006	\N
92	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(9,11)	1028	MethodWithParameters(9,11)	Passed	0.000	\N
97	0	NUnit.Tests.ParameterizedFixture(42).Test1	1031	Test1	Passed	0.000	\N
98	0	NUnit.Tests.ParameterizedFixture(42).Test2	1032	Test2	Passed	0.000	\N
100	0	NUnit.Tests.ParameterizedFixture(5).Test1	1034	Test1	Passed	0.000	\N
101	0	NUnit.Tests.ParameterizedFixture(5).Test2	1035	Test2	Passed	0.000	\N
103	0	NUnit.Tests.Singletons.OneTestCase.TestCase	1013	TestCase	Passed	0.000	\N
105	0	NUnit.Tests.TestAssembly.MockTestFixture.MyTest	1015	MyTest	Passed	0.001	\N
115	0	NUnit.Tests.Assemblies.MockTestFixture.FailingTest	1005	FailingTest	Failed	0.023	116
117	0	NUnit.Tests.Assemblies.MockTestFixture.InconclusiveTest	1010	InconclusiveTest	Inconclusive	0.001	\N
118	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest1	1001	MockTest1	Passed	0.000	\N
120	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest2	1002	MockTest2	Passed	0.000	\N
124	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest3	1003	MockTest3	Passed	0.000	\N
127	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest4	1007	MockTest4	Skipped	0.000	\N
130	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest5	1004	MockTest5	Skipped	0.000	\N
132	0	NUnit.Tests.Assemblies.MockTestFixture.NotRunnableTest	1009	NotRunnableTest	Skipped	0.000	\N
134	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithException	1011	TestWithException	Failed	0.002	135
136	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithManyProperties	1006	TestWithManyProperties	Passed	0.000	\N
143	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(2,2)	1027	MethodWithParameters(2,2)	Passed	0.006	\N
144	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(9,11)	1028	MethodWithParameters(9,11)	Passed	0.000	\N
149	0	NUnit.Tests.ParameterizedFixture(42).Test1	1031	Test1	Passed	0.000	\N
150	0	NUnit.Tests.ParameterizedFixture(42).Test2	1032	Test2	Passed	0.000	\N
152	0	NUnit.Tests.ParameterizedFixture(5).Test1	1034	Test1	Passed	0.000	\N
153	0	NUnit.Tests.ParameterizedFixture(5).Test2	1035	Test2	Passed	0.000	\N
155	0	NUnit.Tests.Singletons.OneTestCase.TestCase	1013	TestCase	Passed	0.000	\N
157	0	NUnit.Tests.TestAssembly.MockTestFixture.MyTest	1015	MyTest	Passed	0.001	\N
167	0	NUnit.Tests.Assemblies.MockTestFixture.FailingTest	1005	FailingTest	Failed	0.023	168
169	0	NUnit.Tests.Assemblies.MockTestFixture.InconclusiveTest	1010	InconclusiveTest	Inconclusive	0.001	\N
170	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest1	1001	MockTest1	Passed	0.000	\N
172	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest2	1002	MockTest2	Passed	0.000	\N
176	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest3	1003	MockTest3	Passed	0.000	\N
179	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest4	1007	MockTest4	Skipped	0.000	\N
182	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest5	1004	MockTest5	Skipped	0.000	\N
184	0	NUnit.Tests.Assemblies.MockTestFixture.NotRunnableTest	1009	NotRunnableTest	Skipped	0.000	\N
186	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithException	1011	TestWithException	Failed	0.002	187
188	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithManyProperties	1006	TestWithManyProperties	Passed	0.000	\N
195	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(2,2)	1027	MethodWithParameters(2,2)	Passed	0.006	\N
196	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(9,11)	1028	MethodWithParameters(9,11)	Passed	0.000	\N
201	0	NUnit.Tests.ParameterizedFixture(42).Test1	1031	Test1	Passed	0.000	\N
202	0	NUnit.Tests.ParameterizedFixture(42).Test2	1032	Test2	Passed	0.000	\N
204	0	NUnit.Tests.ParameterizedFixture(5).Test1	1034	Test1	Passed	0.000	\N
205	0	NUnit.Tests.ParameterizedFixture(5).Test2	1035	Test2	Passed	0.000	\N
207	0	NUnit.Tests.Singletons.OneTestCase.TestCase	1013	TestCase	Passed	0.000	\N
209	0	NUnit.Tests.TestAssembly.MockTestFixture.MyTest	1015	MyTest	Passed	0.001	\N
219	0	NUnit.Tests.Assemblies.MockTestFixture.FailingTest	1005	FailingTest	Failed	0.023	220
221	0	NUnit.Tests.Assemblies.MockTestFixture.InconclusiveTest	1010	InconclusiveTest	Inconclusive	0.001	\N
222	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest1	1001	MockTest1	Passed	0.000	\N
224	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest2	1002	MockTest2	Passed	0.000	\N
228	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest3	1003	MockTest3	Passed	0.000	\N
231	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest4	1007	MockTest4	Skipped	0.000	\N
234	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest5	1004	MockTest5	Skipped	0.000	\N
236	0	NUnit.Tests.Assemblies.MockTestFixture.NotRunnableTest	1009	NotRunnableTest	Skipped	0.000	\N
238	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithException	1011	TestWithException	Failed	0.002	239
240	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithManyProperties	1006	TestWithManyProperties	Passed	0.000	\N
247	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(2,2)	1027	MethodWithParameters(2,2)	Passed	0.006	\N
248	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(9,11)	1028	MethodWithParameters(9,11)	Passed	0.000	\N
253	0	NUnit.Tests.ParameterizedFixture(42).Test1	1031	Test1	Passed	0.000	\N
254	0	NUnit.Tests.ParameterizedFixture(42).Test2	1032	Test2	Passed	0.000	\N
256	0	NUnit.Tests.ParameterizedFixture(5).Test1	1034	Test1	Passed	0.000	\N
257	0	NUnit.Tests.ParameterizedFixture(5).Test2	1035	Test2	Passed	0.000	\N
259	0	NUnit.Tests.Singletons.OneTestCase.TestCase	1013	TestCase	Passed	0.000	\N
261	0	NUnit.Tests.TestAssembly.MockTestFixture.MyTest	1015	MyTest	Passed	0.001	\N
272	0	NUnit.Tests.Assemblies.MockTestFixture.FailingTest	1005	FailingTest	Failed	0.023	273
274	0	NUnit.Tests.Assemblies.MockTestFixture.InconclusiveTest	1010	InconclusiveTest	Inconclusive	0.001	\N
275	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest1	1001	MockTest1	Passed	0.000	\N
277	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest2	1002	MockTest2	Passed	0.000	\N
281	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest3	1003	MockTest3	Passed	0.000	\N
284	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest4	1007	MockTest4	Skipped	0.000	\N
287	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest5	1004	MockTest5	Skipped	0.000	\N
289	0	NUnit.Tests.Assemblies.MockTestFixture.NotRunnableTest	1009	NotRunnableTest	Skipped	0.000	\N
291	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithException	1011	TestWithException	Failed	0.002	292
293	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithManyProperties	1006	TestWithManyProperties	Passed	0.000	\N
300	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(2,2)	1027	MethodWithParameters(2,2)	Passed	0.006	\N
301	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(9,11)	1028	MethodWithParameters(9,11)	Passed	0.000	\N
306	0	NUnit.Tests.ParameterizedFixture(42).Test1	1031	Test1	Passed	0.000	\N
307	0	NUnit.Tests.ParameterizedFixture(42).Test2	1032	Test2	Passed	0.000	\N
309	0	NUnit.Tests.ParameterizedFixture(5).Test1	1034	Test1	Passed	0.000	\N
310	0	NUnit.Tests.ParameterizedFixture(5).Test2	1035	Test2	Passed	0.000	\N
312	0	NUnit.Tests.Singletons.OneTestCase.TestCase	1013	TestCase	Passed	0.000	\N
314	0	NUnit.Tests.TestAssembly.MockTestFixture.MyTest	1015	MyTest	Passed	0.001	\N
325	0	NUnit.Tests.Assemblies.MockTestFixture.FailingTest	1005	FailingTest	Failed	0.023	326
327	0	NUnit.Tests.Assemblies.MockTestFixture.InconclusiveTest	1010	InconclusiveTest	Inconclusive	0.001	\N
328	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest1	1001	MockTest1	Passed	0.000	\N
330	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest2	1002	MockTest2	Passed	0.000	\N
334	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest3	1003	MockTest3	Passed	0.000	\N
337	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest4	1007	MockTest4	Skipped	0.000	\N
340	0	NUnit.Tests.Assemblies.MockTestFixture.MockTest5	1004	MockTest5	Skipped	0.000	\N
342	0	NUnit.Tests.Assemblies.MockTestFixture.NotRunnableTest	1009	NotRunnableTest	Skipped	0.000	\N
344	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithException	1011	TestWithException	Failed	0.002	345
346	0	NUnit.Tests.Assemblies.MockTestFixture.TestWithManyProperties	1006	TestWithManyProperties	Passed	0.000	\N
353	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(2,2)	1027	MethodWithParameters(2,2)	Passed	0.006	\N
354	1	NUnit.Tests.FixtureWithTestCases.MethodWithParameters(9,11)	1028	MethodWithParameters(9,11)	Passed	0.000	\N
359	0	NUnit.Tests.ParameterizedFixture(42).Test1	1031	Test1	Passed	0.000	\N
360	0	NUnit.Tests.ParameterizedFixture(42).Test2	1032	Test2	Passed	0.000	\N
362	0	NUnit.Tests.ParameterizedFixture(5).Test1	1034	Test1	Passed	0.000	\N
363	0	NUnit.Tests.ParameterizedFixture(5).Test2	1035	Test2	Passed	0.000	\N
365	0	NUnit.Tests.Singletons.OneTestCase.TestCase	1013	TestCase	Passed	0.000	\N
367	0	NUnit.Tests.TestAssembly.MockTestFixture.MyTest	1015	MyTest	Passed	0.001	\N
\.


--
-- TOC entry 2179 (class 0 OID 34109)
-- Dependencies: 185
-- Data for Name: testcase_nunit_property; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testcase_nunit_property (testcase_internalid, properties_id) FROM stdin;
14	15
16	17
16	18
16	19
20	21
20	22
23	24
23	25
26	27
28	29
32	33
32	34
66	67
68	69
68	70
68	71
72	73
72	74
75	76
75	77
78	79
80	81
84	85
84	86
118	119
120	121
120	122
120	123
124	125
124	126
127	128
127	129
130	131
132	133
136	137
136	138
170	171
172	173
172	174
172	175
176	177
176	178
179	180
179	181
182	183
184	185
188	189
188	190
222	223
224	225
224	226
224	227
228	229
228	230
231	232
231	233
234	235
236	237
240	241
240	242
275	276
277	278
277	279
277	280
281	282
281	283
284	285
284	286
287	288
289	290
293	294
293	295
328	329
330	331
330	332
330	333
334	335
334	336
337	338
337	339
340	341
342	343
346	347
346	348
\.


--
-- TOC entry 2180 (class 0 OID 34112)
-- Dependencies: 186
-- Data for Name: testgroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testgroup (id, description, name) FROM stdin;
1	test-group-1 description	test-group-1
262	test-group-2 description	test-group-2
315	test-group-3 description	test-group-3
368	test-group-4 description	test-group-4
\.


--
-- TOC entry 2181 (class 0 OID 34118)
-- Dependencies: 187
-- Data for Name: testrun; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testrun (id, asserts, failed, fullname, inconclusive, name, passed, result, rundate, skipped, starttime, testcasecount, "time", total, testgroup_id, testsuite_internalid) FROM stdin;
2	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1	mock-assembly.dll	12	Failed	2011-07-26	3	11:34:27	25	0.154	18	1	3
54	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1	mock-assembly.dll	12	Failed	2011-07-26	3	11:34:27	25	0.154	18	1	55
106	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1	mock-assembly.dll	12	Failed	2011-07-26	3	11:34:27	25	0.154	18	1	107
158	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1	mock-assembly.dll	12	Failed	2011-07-26	3	11:34:27	25	0.154	18	1	159
210	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1	mock-assembly.dll	12	Failed	2011-07-26	3	11:34:27	25	0.154	18	1	211
263	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1	mock-assembly.dll	12	Failed	2011-07-26	3	11:34:27	25	0.154	18	262	264
316	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1	mock-assembly.dll	12	Failed	2011-07-26	3	11:34:27	25	0.154	18	315	317
\.


--
-- TOC entry 2182 (class 0 OID 34124)
-- Dependencies: 188
-- Data for Name: testsuite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testsuite (internalid, asserts, failed, fullname, id, inconclusive, name, passed, result, skipped, testcasecount, "time", total, type, failure_id) FROM stdin;
3	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1036	1	mock-assembly.dll	12	Failed	3	25	0.154	18	Assembly	4
7	0	2	NUnit.Tests.Assemblies.MockTestFixture	1000	1	MockTestFixture	4	Failed	3	11	0.119	10	TestFixture	8
35	0	0	NUnit.Tests.BadFixture	1023	0	BadFixture	0	Skipped	0	1	0.000	0	TestFixture	\N
37	2	0	NUnit.Tests.FixtureWithTestCases	1025	0	FixtureWithTestCases	2	Passed	0	2	0.010	2	TestFixture	\N
38	2	0	NUnit.Tests.FixtureWithTestCases.MethodWithParameters	1026	0	MethodWithParameters	2	Passed	0	2	0.009	2	ParameterizedMethod	\N
41	0	0	NUnit.Tests.IgnoredFixture	1016	0	IgnoredFixture	0	Skipped	0	3	0.000	0	TestFixture	\N
43	0	0	NUnit.Tests.ParameterizedFixture	1029	0	ParameterizedFixture	4	Passed	0	4	0.007	4	ParameterizedFixture	\N
44	0	0	NUnit.Tests.ParameterizedFixture(42)	1030	0	ParameterizedFixture(42)	2	Passed	0	2	0.003	2	TestFixture	\N
47	0	0	NUnit.Tests.ParameterizedFixture(5)	1033	0	ParameterizedFixture(5)	2	Passed	0	2	0.002	2	TestFixture	\N
50	0	0	NUnit.Tests.Singletons.OneTestCase	1012	0	OneTestCase	1	Passed	0	1	0.001	1	TestFixture	\N
52	0	0	NUnit.Tests.TestAssembly.MockTestFixture	1014	0	MockTestFixture	1	Passed	0	1	0.001	1	TestFixture	\N
55	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1036	1	mock-assembly.dll	12	Failed	3	25	0.154	18	Assembly	56
59	0	2	NUnit.Tests.Assemblies.MockTestFixture	1000	1	MockTestFixture	4	Failed	3	11	0.119	10	TestFixture	60
87	0	0	NUnit.Tests.BadFixture	1023	0	BadFixture	0	Skipped	0	1	0.000	0	TestFixture	\N
89	2	0	NUnit.Tests.FixtureWithTestCases	1025	0	FixtureWithTestCases	2	Passed	0	2	0.010	2	TestFixture	\N
90	2	0	NUnit.Tests.FixtureWithTestCases.MethodWithParameters	1026	0	MethodWithParameters	2	Passed	0	2	0.009	2	ParameterizedMethod	\N
93	0	0	NUnit.Tests.IgnoredFixture	1016	0	IgnoredFixture	0	Skipped	0	3	0.000	0	TestFixture	\N
95	0	0	NUnit.Tests.ParameterizedFixture	1029	0	ParameterizedFixture	4	Passed	0	4	0.007	4	ParameterizedFixture	\N
96	0	0	NUnit.Tests.ParameterizedFixture(42)	1030	0	ParameterizedFixture(42)	2	Passed	0	2	0.003	2	TestFixture	\N
99	0	0	NUnit.Tests.ParameterizedFixture(5)	1033	0	ParameterizedFixture(5)	2	Passed	0	2	0.002	2	TestFixture	\N
102	0	0	NUnit.Tests.Singletons.OneTestCase	1012	0	OneTestCase	1	Passed	0	1	0.001	1	TestFixture	\N
104	0	0	NUnit.Tests.TestAssembly.MockTestFixture	1014	0	MockTestFixture	1	Passed	0	1	0.001	1	TestFixture	\N
107	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1036	1	mock-assembly.dll	12	Failed	3	25	0.154	18	Assembly	108
111	0	2	NUnit.Tests.Assemblies.MockTestFixture	1000	1	MockTestFixture	4	Failed	3	11	0.119	10	TestFixture	112
139	0	0	NUnit.Tests.BadFixture	1023	0	BadFixture	0	Skipped	0	1	0.000	0	TestFixture	\N
141	2	0	NUnit.Tests.FixtureWithTestCases	1025	0	FixtureWithTestCases	2	Passed	0	2	0.010	2	TestFixture	\N
142	2	0	NUnit.Tests.FixtureWithTestCases.MethodWithParameters	1026	0	MethodWithParameters	2	Passed	0	2	0.009	2	ParameterizedMethod	\N
145	0	0	NUnit.Tests.IgnoredFixture	1016	0	IgnoredFixture	0	Skipped	0	3	0.000	0	TestFixture	\N
147	0	0	NUnit.Tests.ParameterizedFixture	1029	0	ParameterizedFixture	4	Passed	0	4	0.007	4	ParameterizedFixture	\N
148	0	0	NUnit.Tests.ParameterizedFixture(42)	1030	0	ParameterizedFixture(42)	2	Passed	0	2	0.003	2	TestFixture	\N
151	0	0	NUnit.Tests.ParameterizedFixture(5)	1033	0	ParameterizedFixture(5)	2	Passed	0	2	0.002	2	TestFixture	\N
154	0	0	NUnit.Tests.Singletons.OneTestCase	1012	0	OneTestCase	1	Passed	0	1	0.001	1	TestFixture	\N
156	0	0	NUnit.Tests.TestAssembly.MockTestFixture	1014	0	MockTestFixture	1	Passed	0	1	0.001	1	TestFixture	\N
159	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1036	1	mock-assembly.dll	12	Failed	3	25	0.154	18	Assembly	160
163	0	2	NUnit.Tests.Assemblies.MockTestFixture	1000	1	MockTestFixture	4	Failed	3	11	0.119	10	TestFixture	164
191	0	0	NUnit.Tests.BadFixture	1023	0	BadFixture	0	Skipped	0	1	0.000	0	TestFixture	\N
193	2	0	NUnit.Tests.FixtureWithTestCases	1025	0	FixtureWithTestCases	2	Passed	0	2	0.010	2	TestFixture	\N
194	2	0	NUnit.Tests.FixtureWithTestCases.MethodWithParameters	1026	0	MethodWithParameters	2	Passed	0	2	0.009	2	ParameterizedMethod	\N
197	0	0	NUnit.Tests.IgnoredFixture	1016	0	IgnoredFixture	0	Skipped	0	3	0.000	0	TestFixture	\N
199	0	0	NUnit.Tests.ParameterizedFixture	1029	0	ParameterizedFixture	4	Passed	0	4	0.007	4	ParameterizedFixture	\N
200	0	0	NUnit.Tests.ParameterizedFixture(42)	1030	0	ParameterizedFixture(42)	2	Passed	0	2	0.003	2	TestFixture	\N
203	0	0	NUnit.Tests.ParameterizedFixture(5)	1033	0	ParameterizedFixture(5)	2	Passed	0	2	0.002	2	TestFixture	\N
206	0	0	NUnit.Tests.Singletons.OneTestCase	1012	0	OneTestCase	1	Passed	0	1	0.001	1	TestFixture	\N
208	0	0	NUnit.Tests.TestAssembly.MockTestFixture	1014	0	MockTestFixture	1	Passed	0	1	0.001	1	TestFixture	\N
211	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1036	1	mock-assembly.dll	12	Failed	3	25	0.154	18	Assembly	212
215	0	2	NUnit.Tests.Assemblies.MockTestFixture	1000	1	MockTestFixture	4	Failed	3	11	0.119	10	TestFixture	216
243	0	0	NUnit.Tests.BadFixture	1023	0	BadFixture	0	Skipped	0	1	0.000	0	TestFixture	\N
245	2	0	NUnit.Tests.FixtureWithTestCases	1025	0	FixtureWithTestCases	2	Passed	0	2	0.010	2	TestFixture	\N
246	2	0	NUnit.Tests.FixtureWithTestCases.MethodWithParameters	1026	0	MethodWithParameters	2	Passed	0	2	0.009	2	ParameterizedMethod	\N
249	0	0	NUnit.Tests.IgnoredFixture	1016	0	IgnoredFixture	0	Skipped	0	3	0.000	0	TestFixture	\N
251	0	0	NUnit.Tests.ParameterizedFixture	1029	0	ParameterizedFixture	4	Passed	0	4	0.007	4	ParameterizedFixture	\N
252	0	0	NUnit.Tests.ParameterizedFixture(42)	1030	0	ParameterizedFixture(42)	2	Passed	0	2	0.003	2	TestFixture	\N
255	0	0	NUnit.Tests.ParameterizedFixture(5)	1033	0	ParameterizedFixture(5)	2	Passed	0	2	0.002	2	TestFixture	\N
258	0	0	NUnit.Tests.Singletons.OneTestCase	1012	0	OneTestCase	1	Passed	0	1	0.001	1	TestFixture	\N
260	0	0	NUnit.Tests.TestAssembly.MockTestFixture	1014	0	MockTestFixture	1	Passed	0	1	0.001	1	TestFixture	\N
264	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1036	1	mock-assembly.dll	12	Failed	3	25	0.154	18	Assembly	265
268	0	2	NUnit.Tests.Assemblies.MockTestFixture	1000	1	MockTestFixture	4	Failed	3	11	0.119	10	TestFixture	269
296	0	0	NUnit.Tests.BadFixture	1023	0	BadFixture	0	Skipped	0	1	0.000	0	TestFixture	\N
298	2	0	NUnit.Tests.FixtureWithTestCases	1025	0	FixtureWithTestCases	2	Passed	0	2	0.010	2	TestFixture	\N
299	2	0	NUnit.Tests.FixtureWithTestCases.MethodWithParameters	1026	0	MethodWithParameters	2	Passed	0	2	0.009	2	ParameterizedMethod	\N
302	0	0	NUnit.Tests.IgnoredFixture	1016	0	IgnoredFixture	0	Skipped	0	3	0.000	0	TestFixture	\N
304	0	0	NUnit.Tests.ParameterizedFixture	1029	0	ParameterizedFixture	4	Passed	0	4	0.007	4	ParameterizedFixture	\N
305	0	0	NUnit.Tests.ParameterizedFixture(42)	1030	0	ParameterizedFixture(42)	2	Passed	0	2	0.003	2	TestFixture	\N
308	0	0	NUnit.Tests.ParameterizedFixture(5)	1033	0	ParameterizedFixture(5)	2	Passed	0	2	0.002	2	TestFixture	\N
311	0	0	NUnit.Tests.Singletons.OneTestCase	1012	0	OneTestCase	1	Passed	0	1	0.001	1	TestFixture	\N
313	0	0	NUnit.Tests.TestAssembly.MockTestFixture	1014	0	MockTestFixture	1	Passed	0	1	0.001	1	TestFixture	\N
317	2	2	D:\\Dev\\NUnit\\nunit-3.0\\work\\bin\\vs2008\\Debug\\mock-assembly.dll	1036	1	mock-assembly.dll	12	Failed	3	25	0.154	18	Assembly	318
321	0	2	NUnit.Tests.Assemblies.MockTestFixture	1000	1	MockTestFixture	4	Failed	3	11	0.119	10	TestFixture	322
349	0	0	NUnit.Tests.BadFixture	1023	0	BadFixture	0	Skipped	0	1	0.000	0	TestFixture	\N
351	2	0	NUnit.Tests.FixtureWithTestCases	1025	0	FixtureWithTestCases	2	Passed	0	2	0.010	2	TestFixture	\N
352	2	0	NUnit.Tests.FixtureWithTestCases.MethodWithParameters	1026	0	MethodWithParameters	2	Passed	0	2	0.009	2	ParameterizedMethod	\N
355	0	0	NUnit.Tests.IgnoredFixture	1016	0	IgnoredFixture	0	Skipped	0	3	0.000	0	TestFixture	\N
357	0	0	NUnit.Tests.ParameterizedFixture	1029	0	ParameterizedFixture	4	Passed	0	4	0.007	4	ParameterizedFixture	\N
358	0	0	NUnit.Tests.ParameterizedFixture(42)	1030	0	ParameterizedFixture(42)	2	Passed	0	2	0.003	2	TestFixture	\N
361	0	0	NUnit.Tests.ParameterizedFixture(5)	1033	0	ParameterizedFixture(5)	2	Passed	0	2	0.002	2	TestFixture	\N
364	0	0	NUnit.Tests.Singletons.OneTestCase	1012	0	OneTestCase	1	Passed	0	1	0.001	1	TestFixture	\N
366	0	0	NUnit.Tests.TestAssembly.MockTestFixture	1014	0	MockTestFixture	1	Passed	0	1	0.001	1	TestFixture	\N
\.


--
-- TOC entry 2183 (class 0 OID 34130)
-- Dependencies: 189
-- Data for Name: testsuite_nunit_property; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testsuite_nunit_property (testsuite_internalid, properties_id) FROM stdin;
3	5
3	6
7	9
7	10
35	36
41	42
55	57
55	58
59	61
59	62
87	88
93	94
107	109
107	110
111	113
111	114
139	140
145	146
159	161
159	162
163	165
163	166
191	192
197	198
211	213
211	214
215	217
215	218
243	244
249	250
264	266
264	267
268	270
268	271
296	297
302	303
317	319
317	320
321	323
321	324
349	350
355	356
\.


--
-- TOC entry 2184 (class 0 OID 34133)
-- Dependencies: 190
-- Data for Name: testsuite_testcase; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testsuite_testcase (testsuite_internalid, testcases_internalid) FROM stdin;
7	11
7	13
7	14
7	16
7	20
7	23
7	26
7	28
7	30
7	32
38	39
38	40
44	45
44	46
47	48
47	49
50	51
52	53
59	63
59	65
59	66
59	68
59	72
59	75
59	78
59	80
59	82
59	84
90	91
90	92
96	97
96	98
99	100
99	101
102	103
104	105
111	115
111	117
111	118
111	120
111	124
111	127
111	130
111	132
111	134
111	136
142	143
142	144
148	149
148	150
151	152
151	153
154	155
156	157
163	167
163	169
163	170
163	172
163	176
163	179
163	182
163	184
163	186
163	188
194	195
194	196
200	201
200	202
203	204
203	205
206	207
208	209
215	219
215	221
215	222
215	224
215	228
215	231
215	234
215	236
215	238
215	240
246	247
246	248
252	253
252	254
255	256
255	257
258	259
260	261
268	272
268	274
268	275
268	277
268	281
268	284
268	287
268	289
268	291
268	293
299	300
299	301
305	306
305	307
308	309
308	310
311	312
313	314
321	325
321	327
321	328
321	330
321	334
321	337
321	340
321	342
321	344
321	346
352	353
352	354
358	359
358	360
361	362
361	363
364	365
366	367
\.


--
-- TOC entry 2185 (class 0 OID 34136)
-- Dependencies: 191
-- Data for Name: testsuite_testsuite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY testsuite_testsuite (testsuite_internalid, testsuites_internalid) FROM stdin;
3	7
3	35
3	37
3	41
3	43
3	50
3	52
37	38
43	44
43	47
55	59
55	87
55	89
55	93
55	95
55	102
55	104
89	90
95	96
95	99
107	111
107	139
107	141
107	145
107	147
107	154
107	156
141	142
147	148
147	151
159	163
159	191
159	193
159	197
159	199
159	206
159	208
193	194
199	200
199	203
211	215
211	243
211	245
211	249
211	251
211	258
211	260
245	246
251	252
251	255
264	268
264	296
264	298
264	302
264	304
264	311
264	313
298	299
304	305
304	308
317	321
317	349
317	351
317	355
317	357
317	364
317	366
351	352
357	358
357	361
\.


--
-- TOC entry 2028 (class 2606 OID 34140)
-- Name: failure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY failure
    ADD CONSTRAINT failure_pkey PRIMARY KEY (id);


--
-- TOC entry 2030 (class 2606 OID 34142)
-- Name: nunit_property_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY nunit_property
    ADD CONSTRAINT nunit_property_pkey PRIMARY KEY (id);


--
-- TOC entry 2032 (class 2606 OID 34144)
-- Name: testcase_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testcase
    ADD CONSTRAINT testcase_pkey PRIMARY KEY (internalid);


--
-- TOC entry 2036 (class 2606 OID 34146)
-- Name: testgroup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testgroup
    ADD CONSTRAINT testgroup_pkey PRIMARY KEY (id);


--
-- TOC entry 2040 (class 2606 OID 34148)
-- Name: testrun_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testrun
    ADD CONSTRAINT testrun_pkey PRIMARY KEY (id);


--
-- TOC entry 2042 (class 2606 OID 34150)
-- Name: testsuite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite
    ADD CONSTRAINT testsuite_pkey PRIMARY KEY (internalid);


--
-- TOC entry 2046 (class 2606 OID 34152)
-- Name: uk_f00qwiqm9nvoy2545xxchdj21; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_testcase
    ADD CONSTRAINT uk_f00qwiqm9nvoy2545xxchdj21 UNIQUE (testcases_internalid);


--
-- TOC entry 2034 (class 2606 OID 34154)
-- Name: uk_fmv048hosq7kbxievvbf22gr6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testcase_nunit_property
    ADD CONSTRAINT uk_fmv048hosq7kbxievvbf22gr6 UNIQUE (properties_id);


--
-- TOC entry 2038 (class 2606 OID 34156)
-- Name: uk_imsngcvkmuq6cmep4at6te6n0; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testgroup
    ADD CONSTRAINT uk_imsngcvkmuq6cmep4at6te6n0 UNIQUE (name);


--
-- TOC entry 2048 (class 2606 OID 34158)
-- Name: uk_k2g3k2c9dgaoxg7erc7n6hsvm; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_testsuite
    ADD CONSTRAINT uk_k2g3k2c9dgaoxg7erc7n6hsvm UNIQUE (testsuites_internalid);


--
-- TOC entry 2044 (class 2606 OID 34160)
-- Name: uk_md8btsqx4rnqcgm2g8fgeodkj; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_nunit_property
    ADD CONSTRAINT uk_md8btsqx4rnqcgm2g8fgeodkj UNIQUE (properties_id);


--
-- TOC entry 2052 (class 2606 OID 34161)
-- Name: fk2m0iq6j1iyqxpukdxls6bpmgu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testrun
    ADD CONSTRAINT fk2m0iq6j1iyqxpukdxls6bpmgu FOREIGN KEY (testgroup_id) REFERENCES testgroup(id);


--
-- TOC entry 2053 (class 2606 OID 34166)
-- Name: fk55flin6co91oqtudevf8glksu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testrun
    ADD CONSTRAINT fk55flin6co91oqtudevf8glksu FOREIGN KEY (testsuite_internalid) REFERENCES testsuite(internalid);


--
-- TOC entry 2050 (class 2606 OID 34171)
-- Name: fk7w1gbstx0q1ilqtlj4285d11w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testcase_nunit_property
    ADD CONSTRAINT fk7w1gbstx0q1ilqtlj4285d11w FOREIGN KEY (testcase_internalid) REFERENCES testcase(internalid);


--
-- TOC entry 2057 (class 2606 OID 34176)
-- Name: fk85cr0ok45gkfm7ro66hybatie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_testcase
    ADD CONSTRAINT fk85cr0ok45gkfm7ro66hybatie FOREIGN KEY (testsuite_internalid) REFERENCES testsuite(internalid);


--
-- TOC entry 2059 (class 2606 OID 34181)
-- Name: fke2weyn00yn6dkcre9fwnl7h9y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_testsuite
    ADD CONSTRAINT fke2weyn00yn6dkcre9fwnl7h9y FOREIGN KEY (testsuite_internalid) REFERENCES testsuite(internalid);


--
-- TOC entry 2049 (class 2606 OID 34186)
-- Name: fkgl4hbfv1d872lrgvursdjij2r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testcase
    ADD CONSTRAINT fkgl4hbfv1d872lrgvursdjij2r FOREIGN KEY (failure_id) REFERENCES failure(id);


--
-- TOC entry 2055 (class 2606 OID 34191)
-- Name: fkhx128ndrc6jnxnokhj34vfwvd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_nunit_property
    ADD CONSTRAINT fkhx128ndrc6jnxnokhj34vfwvd FOREIGN KEY (properties_id) REFERENCES nunit_property(id);


--
-- TOC entry 2058 (class 2606 OID 34196)
-- Name: fkorhbjrkr4bujwiwlg43bay9qd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_testcase
    ADD CONSTRAINT fkorhbjrkr4bujwiwlg43bay9qd FOREIGN KEY (testcases_internalid) REFERENCES testcase(internalid);


--
-- TOC entry 2054 (class 2606 OID 34201)
-- Name: fkow39x8fitxmd9yplhv5xalxit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite
    ADD CONSTRAINT fkow39x8fitxmd9yplhv5xalxit FOREIGN KEY (failure_id) REFERENCES failure(id);


--
-- TOC entry 2056 (class 2606 OID 34206)
-- Name: fkpo7r4nlfr5hey9wci9168iuns; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_nunit_property
    ADD CONSTRAINT fkpo7r4nlfr5hey9wci9168iuns FOREIGN KEY (testsuite_internalid) REFERENCES testsuite(internalid);


--
-- TOC entry 2060 (class 2606 OID 34211)
-- Name: fks0rdh8y53ip2p4u6vaoma6bna; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testsuite_testsuite
    ADD CONSTRAINT fks0rdh8y53ip2p4u6vaoma6bna FOREIGN KEY (testsuites_internalid) REFERENCES testsuite(internalid);


--
-- TOC entry 2051 (class 2606 OID 34216)
-- Name: fksisu91hufpt6y83j5qa01c3lg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY testcase_nunit_property
    ADD CONSTRAINT fksisu91hufpt6y83j5qa01c3lg FOREIGN KEY (properties_id) REFERENCES nunit_property(id);


--
-- TOC entry 2192 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-12-03 16:25:19 CET

--
-- PostgreSQL database dump complete
--

